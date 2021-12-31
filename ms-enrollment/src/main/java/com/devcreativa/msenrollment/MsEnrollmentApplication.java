package com.devcreativa.msenrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEnrollmentApplication.class, args);
	}

}
