package com.devcreativa.msinstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsInstructorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInstructorApplication.class, args);
	}

}
