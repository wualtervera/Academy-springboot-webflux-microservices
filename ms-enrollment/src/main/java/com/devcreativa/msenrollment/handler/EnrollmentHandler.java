package com.devcreativa.msenrollment.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.msenrollment.model.entity.Enrollment;
import com.devcreativa.msenrollment.services.EnrollmentService;
import reactor.core.publisher.Mono;

@Component
public class EnrollmentHandler implements IOperations {

  @Autowired
  private EnrollmentService service;

  @Override
  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(this.service.findAll(), Enrollment.class);
  }

  @Override
  public Mono<ServerResponse> findById(ServerRequest request) {
    String id = request.pathVariable("id");
    return this.service.findById(id)
        .flatMap(enrollment -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(enrollment)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  @Override
  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<Enrollment> registrationMono = request.bodyToMono(Enrollment.class);

    return registrationMono.flatMap(enrollment -> this.service.save(enrollment)
        .flatMap(registrationdb -> ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(registrationdb)))
        .switchIfEmpty(ServerResponse.notFound().build()));
  }

  @Override
  public Mono<ServerResponse> update(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Enrollment> registrationMono = request.bodyToMono(Enrollment.class);

    return this.service.findById(id)
        .flatMap(r -> registrationMono
            .flatMap(enrollment -> this.service.update(id, enrollment)
                .flatMap(registrationdb -> ServerResponse.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(registrationdb))))
            .switchIfEmpty(ServerResponse.notFound().build()))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> patch(ServerRequest request) {
    Mono<Enrollment> registrationMono = request.bodyToMono(Enrollment.class);
    return registrationMono
        .flatMap(enrollment -> this.service.patch(enrollment)
            .flatMap(registrationdb -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(registrationdb))))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  @Override
  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    return this.service.findById(id)
        .flatMap(r -> this.service.delete(id).then(ServerResponse.noContent().build()))
        .switchIfEmpty(ServerResponse.notFound().build());

  }

  public Mono<ServerResponse> findByIdStudent(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse.ok()
        .body(this.service.findByIdStudent(id), Enrollment.class);
  }
}
