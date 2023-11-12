package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

@RefreshScope
// une fois un commit fait dans le repositories, En revoit une requete de type post via actuactor.
// Requete : POST localhost:8081/actuator/refresh

public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner Start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("sokaina").email("sokaina@gmail.com").build(),
					Customer.builder().name("rachida").email("rachida@gmail.com").build(),
					Customer.builder().name("siham").email("siham@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
