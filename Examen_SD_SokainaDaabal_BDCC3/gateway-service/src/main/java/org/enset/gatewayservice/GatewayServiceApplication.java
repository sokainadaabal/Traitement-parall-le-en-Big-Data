package org.enset.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
		// À chaque fois que tu reçois une request regrade dans l'URL, tu vas trouver le nom de micro service, il prend ce dernier et router la request vers le bon micro-service.
	DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties){
		return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
	}
}
