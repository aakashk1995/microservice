//package com.example.apigateway.filters;
//
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class FilterConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
//        http.authorizeExchange()
//                .anyExchange()
//                .authenticated()
//                .and().oauth2Login();
//
//        return http.build();
//    }
//}
