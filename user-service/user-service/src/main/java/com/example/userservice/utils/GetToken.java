package com.example.userservice.utils;


import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpSession;

public class GetToken {

    public static String getTokenFromSession(){
        HttpSession session = GetCurrentHttpRequest.getCurrentHttpSession();
        //implement logic to get token from session with attribute SPRING_SECURITY_CONTEXT
        return  null;
    }

    public static String getTokenFromRequestHeaders(){
       return GetCurrentHttpRequest.getRequestHeaders().get("Authorization").get(0);
    }
}
