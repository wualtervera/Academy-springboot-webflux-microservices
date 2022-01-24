package com.devcreativa.msinstructor.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devcreativa.msinstructor.model.dto.CourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CourseClient {
    WebClient webClient = WebClient.create("http://localhost:8080/api/v1/course");


    public Flux<CourseDto> findCoursesInstructorById(String id) {
        return webClient.get()
                .uri("/instructor/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CourseDto.class);
    }

    /*public Flux<CourseDto> findAll() {
        return webClient.get()
            .uri("/instructor")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(CourseDto.class);
    }*/


}
