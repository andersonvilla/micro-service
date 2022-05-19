package com.pragma.imagems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImagemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagemsApplication.class, args);
	}

}
