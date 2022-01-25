package com.devcreativa.mscourse.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devcreativa.mscourse.model.dto.InstructorDto;
import com.devcreativa.mscourse.model.dto.InstructorShowDto;
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

  //Listar coursos con instructor
  public Flux<InstructorShowDto> findAllInstructors() {
    return webClient.get()
        .uri("/")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(InstructorShowDto.class);
  }

  public Mono<InstructorShowDto> findInstructorsById(String id) {
    return webClient.get()
        .uri("/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(InstructorShowDto.class);
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
