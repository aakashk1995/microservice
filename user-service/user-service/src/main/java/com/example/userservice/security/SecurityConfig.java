package com.example.userservice.security;

import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,
        securedEnabled = true,jsr250Enabled  = true)
public class SecurityConfig {

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtGrantedAuthoritiesConverter authenticationConverter= new JwtGrantedAuthoritiesConverter();
//        authenticationConverter.setAuthorityPrefix("ROLE_");
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(authenticationConverter);
//        return converter;
//    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Object> resourceAccess = jwt.getClaim("realm_access");

            List<String> clientRoles = (List<String>) resourceAccess.get("roles");

            //Map<String, Object> clientRoleMap =  resourceAccess;

            //List<String> clientRoles = new ArrayList<>((Integer) clientRoleMap.get("roles"));

            return clientRoles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityConfigurerAdapter securityConfigurerAdapter(){
        return new SecurityConfigurerAdapter() {

            public void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeRequests().anyRequest().authenticated()
                        .and()
                        .oauth2ResourceServer().jwt()
                        .jwtAuthenticationConverter(jwtAuthenticationConverterForKeycloak());
            }
        };
    }
}
