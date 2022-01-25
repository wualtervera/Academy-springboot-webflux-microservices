package com.devcreativa.msstudent.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.devcreativa.msstudent.client.CourseClient;
import com.devcreativa.msstudent.model.dao.StudentDao;
import com.devcreativa.msstudent.model.dto.CoursesDto;
import com.devcreativa.msstudent.model.entity.Student;
import com.devcreativa.msstudent.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentService implements IOpereationServices<Student> {

  @Autowired
  private StudentRepository repository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CourseClient courseClient;

  @Autowired
  private ReactiveCircuitBreakerFactory breakerFactory;

  private Student mapperToStudent(Student s, List<CoursesDto> listCourses) {
    List<CoursesDto> idCourses = s.getCourses();
    List<CoursesDto> list = new ArrayList<>();
    if (idCourses != null) {
      for (CoursesDto cid : idCourses) {
        for (CoursesDto course : listCourses) {
          if (cid.getId().equals(course.getId())) {
            list.add(course);
          }
        }
      }
    }
    Student student = s;
    student.setCourses(list);
    return student;
  }

  @Override
  public Flux<Student> findAll() {
    return this.repository.findAll().map(this::toStudent)
        .flatMap(s -> this.breakerFactory.create("/")
            .run(this.courseClient.findAllCourses()
                    .collectList()
                    .map(listCourses -> mapperToStudent(s, listCourses))
                , throwable -> {
                  s.setCourses(null);
                  return Mono.just(s);
                })
        );
  }

  @Override
  public Mono<Student> findById(String id) {
    return this.repository.findById(id).map(this::toStudent)
        .flatMap(s -> this.breakerFactory.create("/")
            .run(this.courseClient.findAllCourses()
                    .collectList()
                    .map(listCourses -> this.mapperToStudent(s, listCourses))
                , throwable -> {
                  s.setCourses(null);
                  return Mono.just(s);
                })
        );

  }

  @Override
  public Mono<Student> save(Student student) {
    student.setCreatedAt(new Date());
    student.setUpdatedAt(new Date());
    return this.repository.save(this.toStudenDao(student)).map(this::toStudent);
  }

  @Override
  public Mono<Student> update(String id, Student student) {
    return this.repository.findById(id).flatMap(studentx -> {
      student.setId(id);
      student.setCreatedAt(studentx.getCreatedAt());
      student.setUpdatedAt(new Date());
      return this.repository.save(this.toStudenDao(student)).map(this::toStudent);
    });
  }

  @Override
  public Mono<Void> delete(String id) {
    return this.repository.deleteById(id);
  }

  public Student toStudent(StudentDao studentDao) {
    return this.objectMapper.convertValue(studentDao, Student.class);
  }

  public StudentDao toStudenDao(Student student) {
    return this.objectMapper.convertValue(student, StudentDao.class);
  }


}
