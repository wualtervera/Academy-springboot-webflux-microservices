package com.devcreativa.msenrollment.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.devcreativa.msenrollment.model.dto.InstructorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class InstructorClient {
    WebClient webClient = WebClient.create("http://localhost:8080/api/v1/instructor");

    public Mono<InstructorDto> findById(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(InstructorDto.class);
    }

    public Flux<InstructorDto> update(InstructorDto instructor) {
        return webClient.patch()
            .uri("/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(instructor), InstructorDto.class)
            .retrieve()
            .bodyToFlux(InstructorDto.class);
    }
}
