package com.example.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableEurekaClient
@EnableWebSecurity
public class ApiGatewayApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
