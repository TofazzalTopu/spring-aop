package com.info.aop.controller;


import com.info.aop.annotation.ValidateCredentials;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping("/login")
    @ValidateCredentials
    public String getSecureData(@RequestHeader String userName, @RequestHeader String password) {
        return "🔒 Secure Data Accessed!";
    }

}
