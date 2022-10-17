package com.devcreativa.mscourse.services;

import com.devcreativa.mscourse.client.EnrollmentClient;
import com.devcreativa.mscourse.client.InstructorClient;
import com.devcreativa.mscourse.client.StudentClient;
import com.devcreativa.mscourse.model.dao.CourseDao;
import com.devcreativa.mscourse.model.dto.*;
import com.devcreativa.mscourse.model.entity.Course;
import com.devcreativa.mscourse.repositories.CourseRepository;
import com.devcreativa.mscourse.util.LogTracer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseService implements IOpereationServices<Course> {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private InstructorClient instructorClient;

    @Autowired
    private EnrollmentClient enrollmentClient;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ReactiveCircuitBreakerFactory breakerFactory;
    @Autowired
    private LogTracer logTracer;

    @Override
    public Flux<Course> findAll() {

        logTracer.printLog("service_init", "request", "");

        Flux<Course> courses = repository.findAll().map(this::toCourse);

        return courses.doOnNext(course -> {
            logTracer.printLog("service_end", "response", course);
        });

    }

    @Override
    public Mono<Course> findById(String id) {
        //logTracer.printLog("service_response", id);
        return this.repository.findById(id).map(this::toCourse);
    }

    @Override
    public Mono<Course> save(Course course) {
        course.setCreatedAt(new Date());
        course.setUpdatedAt(new Date());
        return this.instructorClient.findById(course.getInstructor().getId())
            .flatMap(instructor -> {
                    course.setInstructor(instructor);
                    return this.repository.save(this.toCourseDao(course)).map(this::toCourse);
                }
            );

    }

    @Override
    public Mono<Course> update(String id, Course course) {
        course.setId(id);
        return this.instructorClient.findById(course.getInstructor().getId())
            .flatMap(instructor ->
                this.repository.findById(id)
                    .flatMap(courseDao -> {
                        course.setCreatedAt(courseDao.getCreatedAt());
                        course.setUpdatedAt(new Date());
                        course.setInstructor(instructor);
                        return this.repository.save(this.toCourseDao(course)).map(this::toCourse);
                    })
            );
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    //Returns courses by instructor id
    public Flux<Course> findCoursesByInstructor(String idInstructor) {
        return this.repository.findByInstructorId(idInstructor).map(this::toCourse);
    }

    //Return alls courses more instructor
    public Mono<List<CourseShowDto>> findAllCourseMoreInstructor() {
        return this.repository.findAll().map(this::toCourse).collectList()
            .flatMap(c -> breakerFactory.create("instructor")
                .run(this.instructorClient.findAllInstructors()
                        .collectList()
                        .map(i -> this.maperCoursesShow(c, i)),
                    throwable -> {
                        CourseShowDto course = new CourseShowDto();
                        List<CourseShowDto> list = new ArrayList<>();
                        for (Course cx : c) {
                            list.add(this.toCourseShowDto(course, cx, null));
                        }
                        return Mono.just(list);
                    }
                )
            );
    }

    //Return one courses more instructor
    public Mono<CourseShowDto> findCourseMoreInstructorById(String id) {
        return this.repository.findById(id).map(this::toCourse)
            .flatMap(c -> this.breakerFactory.create("instructor/{id}")
                .run(this.instructorClient.findInstructorsById(c.getInstructor().getId())
                    .map(i -> {
                            CourseShowDto courseShowDto = this.toOneCourseShowDto(c);
                            courseShowDto.setInstructor(i);
                            return courseShowDto;
                        }
                    ), throwable -> Mono.just(this.toCourseShowDto(new CourseShowDto(), c, null))
                )
            );

    }

    // returns the student and the purchased course
    public Mono<StudentDto> paymetCourse(PaymentDto paymentDto) {
        return this.findById(paymentDto.getCourse().getId())
            .switchIfEmpty(Mono.error(new NoSuchElementException("Course not found")))
            .flatMap(course -> this.studentClient.findById(paymentDto.getStudent().getId())
                .flatMap(s -> {
                    StudentDto student = new StudentDto();
                    List<CourseDto> courseList = new ArrayList<>();
                    CourseDto courseDto = new CourseDto();
                    courseDto.setId(course.getId());
                    if (s.getCourses() != null) {
                        courseList = s.getCourses().stream()
                            .filter(cx -> !cx.getId().equals(paymentDto.getCourse().getId()))
                            .collect(Collectors.toList());
                    }
                    courseList.add(courseDto);
                    student.setId(s.getId());
                    student.setName(s.getName());
                    student.setEmail(s.getEmail());
                    student.setPassword(s.getPassword());
                    student.setCourses(courseList);
                    return this.studentClient.save(student)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Error inesperado")));
                })
            );
    }

    public List<CourseShowDto> maperCoursesShow(List<Course> c, List<InstructorShowDto> i) {
        CourseShowDto course = new CourseShowDto();
        List<CourseShowDto> list = new ArrayList<>();
        for (Course cx : c) {
            for (InstructorShowDto ix : i) {
                if (cx.getInstructor().getId().equals(ix.getId())) {
                    list.add(this.toCourseShowDto(course, cx, ix));
                }
            }
        }
        return list;
    }

    public CourseShowDto toCourseShowDto(CourseShowDto course, Course c, InstructorShowDto i) {
        course.setId(c.getId());
        course.setName(c.getName());
        course.setDescription(c.getDescription());
        course.setPrice(c.getPrice());
        course.setUrlImage(c.getUrlImage());
        course.setRating(c.getRating());
        course.setInstructor(i);
        course.setCreatedAt(c.getCreatedAt());
        course.setUpdatedAt(c.getUpdatedAt());
        return course;
    }

    //Maper
    public Course toCourse(CourseDao courseDao) {
        return mapper.convertValue(courseDao, Course.class);
    }

    public CourseShowDto toOneCourseShowDto(Course course) {
        return mapper.convertValue(course, CourseShowDto.class);
    }

    public CourseDao toCourseDao(Course course) {
        return mapper.convertValue(course, CourseDao.class);
    }
}
