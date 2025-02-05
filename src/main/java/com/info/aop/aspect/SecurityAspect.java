package com.info.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    private final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    @Pointcut("execution(* com.info.aop.controller.SecureController.*(..))")
    public void secureEndpoints() {
    }

    @Before("secureEndpoints()")
    public void checkAuthentication() {
        // Simulate authentication logic
        boolean isAuthenticated = Math.random() > 0.5; // Random true/false for demo
        try {
            if (!isAuthenticated) throw new SecurityException("🚫 Access Denied: User not authenticated!");

        } catch (SecurityException e) {
            logger.error(e.getMessage());
        }
        logger.info("✅ Authentication successful!");
    }

}

