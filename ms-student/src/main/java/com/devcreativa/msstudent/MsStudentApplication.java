package com.devcreativa.msstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStudentApplication.class, args);
	}

}
