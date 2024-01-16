package com.bezkoder.spring.jpa.h2;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bezkoder.spring.jpa.h2.model.Seats;
import com.bezkoder.spring.jpa.h2.repository.SeatsRepository;

@SpringBootApplication
public class SpringBootJpaH2Application {
	@Autowired
	static SeatsRepository seatsRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	

	}

}
