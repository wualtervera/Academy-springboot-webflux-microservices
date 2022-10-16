package com.devcreativa.mscourse.handler;

import com.devcreativa.mscourse.util.LogTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.mscourse.client.InstructorClient;
import com.devcreativa.mscourse.model.dto.CourseShowDto;
import com.devcreativa.mscourse.model.dto.PaymentDto;
import com.devcreativa.mscourse.model.entity.Course;
import com.devcreativa.mscourse.services.CourseService;
import reactor.core.publisher.Mono;


@Component
public class CourseHandler implements IOperations {
  @Autowired
  private CourseService service;

  @Autowired
  private InstructorClient instructorClient;

  @Autowired
  private LogTracer logTracer;

  @Override
  public Mono<ServerResponse> findAll(ServerRequest request) {
    logTracer.printLog("controller", request.uri());
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(this.service.findAll(), Course.class);
  }

  @Override
  public Mono<ServerResponse> findById(ServerRequest request) {
    String id = request.pathVariable("id");
    return this.service.findById(id)
        .flatMap(course -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(course)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  //Return alls courses more instructor
  public Mono<ServerResponse> findAllCourseMoreInstructor(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(this.service.findAllCourseMoreInstructor(), CourseShowDto.class);
  }

  //Return one courses more instructor
  public Mono<ServerResponse> findCourseMoreInstructorById(ServerRequest request) {
    String id = request.pathVariable("id");
    return this.service.findCourseMoreInstructorById(id)
        .flatMap(course -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(course)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  //Returns courses by instructor id
  public Mono<ServerResponse> findCoursesByInstructor(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(this.service.findCoursesByInstructor(id), Course.class);
  }

  @Override
  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<Course> courseMono = request.bodyToMono(Course.class);
    return courseMono.flatMap(course -> this.service.save(course)
        .flatMap(coursedb -> ServerResponse
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(coursedb))));
  }

  // returns the student and the purchased course
  public Mono<ServerResponse> paymetCourse(ServerRequest request) {
    Mono<PaymentDto> enrollmentMono = request.bodyToMono(PaymentDto.class);
    return enrollmentMono.flatMap(enrollment -> {
      return this.service.paymetCourse(enrollment)
          .flatMap(student -> ServerResponse
              .status(HttpStatus.CREATED)
              .contentType(MediaType.APPLICATION_JSON)
              .body(BodyInserters.fromValue(student)));
    });
  }

  @Override
  public Mono<ServerResponse> update(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Course> courseMono = request.bodyToMono(Course.class);

    return this.service.findById(id)
        .flatMap(c -> courseMono.flatMap(course -> this.service.update(id, course)
            .flatMap(coursedb -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(coursedb)))))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  @Override
  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");

    return this.service.findById(id)
        .flatMap(course -> this.service.delete(id)
            .then(ServerResponse.noContent().build()))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

}
