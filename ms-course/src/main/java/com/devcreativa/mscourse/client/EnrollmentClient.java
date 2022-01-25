package com.devcreativa.mscourse.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.devcreativa.mscourse.model.dto.PaymentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EnrollmentClient {
  WebClient webClient = WebClient.create("http://localhost:8080/api/v1/enrollment");


  public Mono<PaymentDto> findById(String id) {
    return webClient.get()
        .uri("/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(PaymentDto.class);
  }

  public Flux<PaymentDto> findAll() {
    return webClient.get()
        .uri("/")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(PaymentDto.class);
  }

  public Mono<PaymentDto> save(PaymentDto enrollment) {
    return webClient.patch()
        .uri("/")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(enrollment), PaymentDto.class)
        .retrieve()
        .bodyToMono(PaymentDto.class);
  }

  public Mono<PaymentDto> update(PaymentDto enrollment) {
    return webClient.patch()
        .uri("/")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(enrollment), PaymentDto.class)
        .retrieve()
        .bodyToMono(PaymentDto.class);
  }
}
