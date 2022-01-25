package com.devcreativa.mscourse.client;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
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
        .onStatus(status -> status.value() == HttpStatus.METHOD_NOT_ALLOWED.value(),
            response -> Mono.error(new NoSuchElementException("Student not found")))
        .bodyToMono(StudentDto.class);
  }

  public Flux<StudentDto> findAll() {
    return webClient.get()
        .uri("/")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(StudentDto.class);
  }

  public Mono<StudentDto> save(StudentDto student) {
    return webClient.post()
        .uri("/")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(student), StudentDto.class)
        .retrieve()
        .bodyToMono(StudentDto.class);
  }


}
