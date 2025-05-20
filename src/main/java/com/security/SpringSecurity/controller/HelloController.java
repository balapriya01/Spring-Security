package com.security.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    
    
    @GetMapping("/")
    public static String greet(HttpServletRequest request){
        return "Welcome to my Bookstore  " + request.getSession().getId();
    }

}
