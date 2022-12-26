package com.example.apigateway.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import java.security.Principal;

@RestController
public class ControllerClass {

    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    }


    @GetMapping("/ping")
    public String ping() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities();
    }

    @GetMapping("/session")
    public String ping2(WebSession session) {
        System.out.println(session.getId());
        System.out.println(session);
      SecurityContext context =   session.getAttribute("SPRING_SECURITY_CONTEXT");
      Authentication authentication = context.getAuthentication();
     return session.getId();
    }

}
