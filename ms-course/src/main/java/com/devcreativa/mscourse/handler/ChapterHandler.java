package com.devcreativa.mscourse.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.mscourse.model.entity.Chapter;
import com.devcreativa.mscourse.services.ChapterService;
import reactor.core.publisher.Mono;

@Component
public class ChapterHandler implements IOperations {

    @Autowired
    private ChapterService service;

    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.service.findAll(), Chapter.class);
    }

    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");

        return this.service.findById(id)
                .flatMap(chapter -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(chapter)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Chapter> chapterMono = request.bodyToMono(Chapter.class);
        return chapterMono.flatMap(chapter -> this.service.save(chapter)
                .flatMap(chapterdb -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(chapterdb))));
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Chapter> chapterMono = request.bodyToMono(Chapter.class);

        return this.service.findById(id).flatMap(c -> chapterMono
                        .flatMap(chapter -> this.service.update(id, chapter)
                                .flatMap(chapterdb -> ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(chapterdb)))))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return this.service.findById(id)
                .flatMap(chapter -> this.service.delete(id)
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }
}
