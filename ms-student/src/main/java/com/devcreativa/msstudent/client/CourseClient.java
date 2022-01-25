package com.devcreativa.msstudent.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devcreativa.msstudent.model.dto.CoursesDto;
import reactor.core.publisher.Flux;

@Component
public class CourseClient {
    WebClient webClient = WebClient.create("http://localhost:8080/api/v1/course");


    public Flux<CoursesDto> findAllCourses() {
        return webClient.get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CoursesDto.class);
    }

    /*public Flux<CourseDto> findAll() {
        return webClient.get()
            .uri("/instructor")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(CourseDto.class);
    }*/


}
