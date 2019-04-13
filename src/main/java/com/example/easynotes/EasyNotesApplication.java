package com.example.easynotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNotesApplication extends SpringBootServletInitializer{

	private Logger logger = LoggerFactory.getLogger(EasyNotesApplication.class);
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(EasyNotesApplication.class);
	 }

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesApplication.class, args);
	}
}
