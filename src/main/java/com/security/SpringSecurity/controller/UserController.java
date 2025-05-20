package com.security.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurity.model.Users;
import com.security.SpringSecurity.service.UserService;

@RestController
public class UserController {

    //We are creating a object of userService
    @Autowired
    private UserService service;


    
    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }
}
