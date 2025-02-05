package com.info.aop.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String processPayment(String amount) {
        System.out.println("Processing payment of $" + amount);
        return amount;
    }
}
