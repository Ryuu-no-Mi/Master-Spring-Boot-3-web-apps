package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:ValidationMessages.properties")
public class SpringbootCrudJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudJpaApplication.class, args);
	}

}
