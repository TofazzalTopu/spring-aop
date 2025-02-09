package com.info.aop.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WelcomeController {


    @RequestMapping
    public String getMessage() {
        return "Welcome to Spring AOP!";
    }


}
