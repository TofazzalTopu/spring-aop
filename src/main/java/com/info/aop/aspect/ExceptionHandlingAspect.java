package com.info.aop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.info.aop.service.*.*(..))", throwing = "ex")
    public void handleException(Exception ex) {
        logger.error("❌ Exception caught: " + ex.getMessage());
    }
}
