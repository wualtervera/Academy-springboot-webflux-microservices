package com.devcreativa.msstudent.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.msstudent.model.entity.Student;
import com.devcreativa.msstudent.services.StudentService;
import reactor.core.publisher.Mono;

@Component
public class StudenHandler implements IOperations {

    @Autowired
    private StudentService service;

    @Override
    public Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.service.findAll(), Student.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> findById(final ServerRequest request) {
        return this.service.findById(request.pathVariable("id"))
                .flatMap(student -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(student)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> save(final ServerRequest request) {
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return studentMono.flatMap(student -> this.service.save(student)
                .flatMap(studentdb -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(studentdb))));
    }

    @Override
    public Mono<ServerResponse> update(final ServerRequest request) {
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        String id = request.pathVariable("id");

        return this.service.findById(id)
                .flatMap(s -> studentMono
                        .flatMap(student -> this.service.update(id, student)
                                .flatMap(studentdb -> ServerResponse
                                        .status(HttpStatus.CREATED)
                                        .contentType(APPLICATION_JSON)
                                        .body(fromValue(studentdb)))))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> delete(final ServerRequest request) {
        String id = request.pathVariable("id");

        return this.service.findById(id)
                .flatMap(student -> this.service.delete(id)
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }
}
