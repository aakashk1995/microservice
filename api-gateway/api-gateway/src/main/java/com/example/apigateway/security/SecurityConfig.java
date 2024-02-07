//package com.example.apigateway.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain  springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
////        http
////                .authorizeExchange()
////                .pathMatchers("/actuator/**", "/","/logout.html","/login")
////                .permitAll()
////                .and()
////                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().authenticated())
////                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
////                .csrf().disable();
//               // .and()
//              //  .oauth2Login(); // to redirect to oauth2 login page.
//        serverHttpSecurity.authorizeExchange().pathMatchers("./actuator/**","/")
//                        .permitAll();
//        serverHttpSecurity.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
//                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
//                        .oauth2Login();
//
//      //  serverHttpSecurity.authorizeExchange().anyExchange().authenticated().and().oauth2Login();
//
//        serverHttpSecurity.csrf().disable();
//        return serverHttpSecurity.build();
//
//    }
//}
