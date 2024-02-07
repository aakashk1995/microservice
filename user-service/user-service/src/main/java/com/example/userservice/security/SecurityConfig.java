package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,
        securedEnabled = true,jsr250Enabled  = true)
public class SecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter authenticationConverter= new JwtGrantedAuthoritiesConverter();
        authenticationConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authenticationConverter);
        return converter;
    }

    @Bean
    public SecurityConfigurerAdapter securityConfigurerAdapter(){
        return new SecurityConfigurerAdapter() {

            public void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeRequests().anyRequest().authenticated()
                        .and()
                        .oauth2ResourceServer().jwt()
                        .jwtAuthenticationConverter(jwtAuthenticationConverter());
            }
        };
    }
}
