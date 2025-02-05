package com.info.aop.controller;


import com.info.aop.User;
import com.info.aop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PaymentService paymentService; // This is the PaymentService class that is being injected into the UserController class

    @PostMapping
    public User save(@RequestBody User user) {
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return new User(id, "John Doe");
    }

    @GetMapping("/payment")
    public String processPayment(@RequestParam String amount) {
        return  paymentService.processPayment(amount);
    }

}
