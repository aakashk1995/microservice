package com.example.userservice.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class OAuthFeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            // Retrieve the OAuth token from the SecurityContext or wherever you have stored it
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
        } catch (OAuth2AuthenticationException e) {
            // Handle exception or log as needed
            System.out.println("Exception while intercepting Token in OAuthFeignRequestInterceptor !!");
            throw new RuntimeException();
        }
    }
}
