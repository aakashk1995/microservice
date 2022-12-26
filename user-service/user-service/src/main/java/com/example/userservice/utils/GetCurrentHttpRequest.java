package com.example.userservice.utils;

import com.netflix.discovery.shared.transport.EurekaHttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetCurrentHttpRequest {

    public static HttpServletRequest getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .get();
    }

    public  static HttpSession getCurrentHttpSession(){
        return getCurrentHttpRequest().getSession();
    }

    public static HttpHeaders getRequestHeaders() {
        HttpHeaders httpHeaders = Collections.list(getCurrentHttpRequest().getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(getCurrentHttpRequest().getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
       return httpHeaders;
    }
}
