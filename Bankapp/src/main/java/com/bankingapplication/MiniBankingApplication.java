package com.bankingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankingApplication.class, args);
	}

}


//spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
//spring.datasource.username=root
//spring.datasource.password=raju123
//spring.jpa.hibernate.ddl-auto=update