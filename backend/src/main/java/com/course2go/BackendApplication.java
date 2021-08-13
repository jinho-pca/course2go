package com.course2go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/course2go");
		SpringApplication.run(BackendApplication.class, args);
	}

}