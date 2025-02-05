package com.info.aop.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {


    @RequestMapping("/message")
    public String getMessage() {
        return "This is a secure message!";
    }

    @RequestMapping("/admin")
    public String getAdminMessage() {
        return "This is a secure admin message!";
    }

}
