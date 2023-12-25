package org.enset.keynoteservicesd;

import org.enset.keynoteservicesd.dtos.KeynoteDTO;
import org.enset.keynoteservicesd.entities.Keynote;
import org.enset.keynoteservicesd.services.KeynoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class KeynoteServiceSdApplication {
	public static void main(String[] args) {
		SpringApplication.run(KeynoteServiceSdApplication.class, args);
	}
	@Bean
	CommandLineRunner start(RepositoryRestConfiguration restConfiguration, KeynoteService keynoteService) {
		restConfiguration.exposeIdsFor(Keynote.class);
		restConfiguration.exposeIdsFor(KeynoteDTO.class);
		return args -> {
			for (String nom : Arrays.asList("SokainaDaabal", "Ahmed Mohamed", "Yassine")) {
				KeynoteDTO keynoteDTO = new KeynoteDTO();
				keynoteDTO.setFirstName(nom);
				keynoteDTO.setLastName(nom + "22");
				keynoteDTO.setFunction("FULLSTACK");
				keynoteService.saveKeynote(keynoteDTO);
			}
		};
	}
}
