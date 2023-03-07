package com.keduit.bpro051;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

//[추가] 패키지 domain에있는 BaseEntity를 만든후 아래 @추가
@EnableJpaAuditing
public class Bpro051Application {

	public static void main(String[] args) {
		SpringApplication.run(Bpro051Application.class, args);
	}

}
