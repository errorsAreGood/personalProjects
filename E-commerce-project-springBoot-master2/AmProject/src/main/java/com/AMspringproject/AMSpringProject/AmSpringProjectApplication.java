package com.AMspringproject.AMSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class AmSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmSpringProjectApplication.class, args);
	}

}
