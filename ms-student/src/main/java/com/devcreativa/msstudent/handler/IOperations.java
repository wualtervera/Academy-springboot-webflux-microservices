package com.devcreativa.msstudent.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IOperations {
    Mono<ServerResponse> findAll(ServerRequest request);

    Mono<ServerResponse> findById(ServerRequest request);

    Mono<ServerResponse> save(ServerRequest request);

    Mono<ServerResponse> update(ServerRequest request);

    Mono<ServerResponse> delete(ServerRequest request);
}
