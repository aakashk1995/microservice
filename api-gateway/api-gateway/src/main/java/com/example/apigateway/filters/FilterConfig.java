package com.example.apigateway.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class FilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter requestFilter(){
        return (exchange, chain) -> {
            //PRE FILTER LOGIC
//            ServerHttpRequest request = exchange.getRequest();
//            Mono<WebSession> sessionMono = exchange.getSession();
//            HttpHeaders httpHeaders =  request.getHeaders();
//           List<String> headersValueList =  httpHeaders.get("Authorization");
//            System.out.println(headersValueList);
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // POST FILTER RESPONSE LOGIC
//                        var response = exchange.getResponse();
//                        response.setRawStatusCode(201);
//                        exchange.mutate().response(response).build();
                    }));
        };
    }
}
