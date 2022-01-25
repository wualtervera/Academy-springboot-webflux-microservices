package com.devcreativa.msinstructor.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import com.devcreativa.msinstructor.client.CourseClient;
import com.devcreativa.msinstructor.model.dao.InstructorDao;
import com.devcreativa.msinstructor.model.entity.Instructor;
import com.devcreativa.msinstructor.repositories.InstructorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InstructorService implements IOpereationServices<Instructor> {
  @Autowired
  private InstructorRepository repository;

  @Autowired
  private CourseClient courseClient;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ReactiveCircuitBreakerFactory breakerFactory;

  @Override
  public Flux<Instructor> findAll() {
    return this.repository.findAll().map(this::toInstructor)
        .flatMap(i -> this.breakerFactory.create("instructor/")
            .run(this.courseClient.findCoursesInstructorById(i.getId())
                .collectList()
                .map(listCourses -> {
                  Instructor instructor = i;
                  instructor.setCourses(listCourses);
                  return instructor;
                }),throwable -> Mono.just(i))
        );
  }

  @Override
  public Mono<Instructor> findById(String id) {
    return this.repository.findById(id).map(this::toInstructor)
        .flatMap(i -> this.breakerFactory.create("instructor/")
            .run(this.courseClient.findCoursesInstructorById(i.getId())
                .collectList()
                .map(listCourses -> {
                  Instructor instructor = i;
                  instructor.setCourses(listCourses);
                  return instructor;
                }),throwable -> Mono.just(i))
        );
  }

  @Override
  public Mono<Instructor> save(Instructor instructor) {
    instructor.setCreatedAt(new Date());
    instructor.setUpdatedAt(new Date());
    return this.repository.save(this.toInstructorDao(instructor)).map(this::toInstructor);
  }

  @Override
  public Mono<Instructor> update(String id, Instructor instructor) {

    return this.repository.findById(id).flatMap(i -> {
      instructor.setId(id);
      instructor.setCreatedAt(i.getCreatedAt());
      instructor.setUpdatedAt(new Date());
      return this.repository.save(this.toInstructorDao(instructor)).map(this::toInstructor);
    });

  }

  @Override
  public Mono<Void> delete(String id) {
    return this.repository.deleteById(id);
  }

  public Instructor toInstructor(InstructorDao instructorDao) {
    return objectMapper.convertValue(instructorDao, Instructor.class);
  }

  public InstructorDao toInstructorDao(Instructor instructor) {
    return objectMapper.convertValue(instructor, InstructorDao.class);
  }
}
