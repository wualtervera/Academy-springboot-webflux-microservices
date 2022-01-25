package com.devcreativa.msinstructor.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.msinstructor.model.entity.Instructor;
import com.devcreativa.msinstructor.services.InstructorService;
import reactor.core.publisher.Mono;

@Component
public class InstructorHandler implements IOperations {
    @Autowired
    private InstructorService service;

    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.service.findAll(), Instructor.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        return service.findById(request.pathVariable("id"))
                .flatMap(instructor -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(instructor)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Instructor> instructorMono = request.bodyToMono(Instructor.class);
        return instructorMono.flatMap(instructor -> this.service.save(instructor)
                .flatMap(instructordb -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(instructordb))));
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Instructor> instructorMono = request.bodyToMono(Instructor.class);
        String id = request.pathVariable("id");

        return this.service.findById(id)
                .flatMap(s -> instructorMono.flatMap(instructor -> this.service.update(id, instructor)
                        .flatMap(instructordb -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(instructordb)))))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.service.findById(id)
                .flatMap(instructor -> this.service.delete(id)
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
