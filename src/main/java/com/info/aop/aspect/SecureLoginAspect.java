package com.info.aop.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static com.info.aop.util.ParseUtil.isNullOrEmpty;

@Aspect
@Component
public class SecureLoginAspect {
    private final Logger logger = LoggerFactory.getLogger(SecureLoginAspect.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpServletRequest request;

    public SecureLoginAspect(HttpServletRequest request) {
        this.request = request;
    }

    private static final String USER_NAME = "rana";
    private static final String PASSWORD = "123";

    @Pointcut("execution(* com.info.aop.controller.*.*(..))")
//    @Pointcut("@annotation(com.info.aop.annotation.ValidateLoginCredentials)")
    public void validateLoginCredentialsPointcut() {
    }

    @Before("validateLoginCredentialsPointcut()")
    public void validateLoginCredentials(JoinPoint joinPoint) {
        // Extract headers from the HTTP request
        String userName = request.getHeader("userName"), password = request.getHeader("password");
        logger.info("userName: {} password: {}",userName, password);

        if(isNullOrEmpty(userName, password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        // Perform validation (this logic can be customized)
        if (!userName.equals(USER_NAME) || !password.equals(PASSWORD)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        logger.info("Credentials validated for method: {} ", ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }


    @After("validateLoginCredentials()")
    public Object logAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object result = joinPoint.proceed();
        logger.info("📤 Response from: " + methodName + " => " + objectMapper.writeValueAsString(result));
        return result;
    }


}
