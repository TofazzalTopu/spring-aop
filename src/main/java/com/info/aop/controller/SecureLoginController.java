package com.info.aop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class SecureLoginController {

    @GetMapping
//    @ValidateLoginCredentials
    public String login(@RequestHeader String userName, @RequestHeader String password) {
        return "🔒 Secure Data Accessed!";
    }

}
