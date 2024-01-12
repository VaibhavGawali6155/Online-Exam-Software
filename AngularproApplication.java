package com.project.angularpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
public class AngularproApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularproApplication.class, args);
		System.err.println("Spring is runing...");
	}

}
