package com.info.aop.controller;


import com.google.gson.Gson;
import com.info.aop.User;
import com.info.aop.exception.CustomException;
import com.info.aop.service.PaymentService;
import com.info.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PaymentService paymentService; // This is the PaymentService class that is being injected into the UserController class

    @Autowired private UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return new User(id, "John Doe");
    }

    @GetMapping("/exception")
    public User getException() throws CustomException {
        return userService.getUser("1");
    }

    @GetMapping("/payment")
    public String processPayment(@RequestParam String amount) {
        return  paymentService.processPayment(amount);
    }


    @PostMapping("/user")
    public User convrtUser(@RequestBody String user){
        Gson gson = new Gson();
        User usr = gson.fromJson(user, User.class);
        return usr;
    }
}
