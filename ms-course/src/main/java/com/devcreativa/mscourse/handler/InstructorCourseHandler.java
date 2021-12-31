package com.devcreativa.mscourse.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.mscourse.model.entity.InstructorCourse;
import com.devcreativa.mscourse.services.InstructorCourseService;
import reactor.core.publisher.Mono;

@Component
public class InstructorCourseHandler implements IOperations {

    @Autowired
    private InstructorCourseService service;

    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.service.findAll(), InstructorCourse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.service.findById(id)
                .flatMap(instructorCourse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(instructorCourse)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<InstructorCourse> icourseMono = request.bodyToMono(InstructorCourse.class);
        return icourseMono.flatMap(instructorCourse -> this.service.save(instructorCourse)
                .flatMap(instructorCoursedb -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(instructorCoursedb))));
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<InstructorCourse> icourseMono = request.bodyToMono(InstructorCourse.class);

        return this.service.findById(id)
                .flatMap(ic -> icourseMono.flatMap(instructorCourse -> this.service.update(id, instructorCourse)
                        .flatMap(instructorCoursedb -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(instructorCoursedb)))))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.service.findById(id)
                .flatMap(ic -> this.service.delete(id)
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
