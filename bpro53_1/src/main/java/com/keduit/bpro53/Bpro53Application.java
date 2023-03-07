package com.keduit.bpro53;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//TODO [추가]
@EnableJpaAuditing
public class Bpro53Application {

	public static void main(String[] args) {
		SpringApplication.run(Bpro53Application.class, args);
	}

}
