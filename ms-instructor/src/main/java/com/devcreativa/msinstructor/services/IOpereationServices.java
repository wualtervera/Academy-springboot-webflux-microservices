package com.devcreativa.msinstructor.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOpereationServices <T>{
    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<T> save(T t);

    Mono<T> update(String id, T t);

    Mono<Void> delete(String id);

}
