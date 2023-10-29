package org.sid.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetewayApplication.class, args);
	}

	//@Bean
	RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
		// on le fait lorsque connaitre les micro service
		return routeLocatorBuilder.routes()
				.route((r)->r.path("/customers/**").uri("lb://CUSTOMER-SERVER"))
				.route((r)->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
				.build();
	}
	@Bean
	// a chaque fois que tu recoit une requete regrade dans l'url tu va trouver le nom de micro service,il prend ce dernier et router la requete vers le bon micro-service.
	DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties){
		return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
	}
}
