package com.nidonoga.person.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayCnfiguration {
	
	@Bean
	RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		// Route redirection
		
		return builder.routes()
				/*
				.route(p -> p.path("/get") // Path capture example
				.filters(f -> 
					f.addRequestHeader("TesteRequestHeader", "TesteRequestHeader") // Example adding parameter to header
					.addRequestParameter("TesteRequestParameter", "TesteRequestParameter")) // Example adding parameter
				.uri("http://httpbin.org:80")) // Redirect example
				*/
				
				.route(p -> p.path("/user-service/**").uri("lb://user-service")) // Redirecting all requests from "/user-service" to the "user-service load balancer"
				.route(p -> p.path("/person-service/**").uri("lb://person-service")).build(); // Redirecting all requests from "/person-service" to the "person-service load balancer"
	}
	

}
