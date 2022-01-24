package com.devcreativa.mscourse.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devcreativa.mscourse.model.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentClient {
    WebClient webClient = WebClient.create("http://localhost:8080/api/v1/student");


    public Mono<StudentDto> findById(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StudentDto.class);
    }

    public Flux<StudentDto> findAll() {
        return webClient.get()
            .uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(StudentDto.class);
    }

    public Flux<StudentDto> update(StudentDto student) {
        return webClient.patch()
            .uri("/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(student), StudentDto.class)
            .retrieve()
            .bodyToFlux(StudentDto.class);
    }


}
