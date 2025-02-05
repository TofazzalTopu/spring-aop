package com.info.aop.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before("execution(* com.info.aop.service.PaymentService.processPayment(..))")
    public void logBefore() {
        logger.info("\nLogging: Payment processing started...");
    }

    @After("execution(* com.info.aop.service.PaymentService.processPayment(..))")
    public void logAfter() {
        logger.info("\nLogging: Payment processing ends.");
    }

    @Around("execution(* com.info.aop.controller.*.*(..))")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("📥 Request to: " + methodName + " with args: " + objectMapper.writeValueAsString(args));

        Object result = joinPoint.proceed();
        logger.info("📤 Response from: " + methodName + " => " + objectMapper.writeValueAsString(result));
        return result;
    }

}

