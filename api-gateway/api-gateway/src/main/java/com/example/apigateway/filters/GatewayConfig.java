package com.example.apigateway.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewayConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http.oauth2ResourceServer().jwt();

        http.authorizeExchange().anyExchange().authenticated();
        http.csrf().disable();
        return http.build();
    }
}
