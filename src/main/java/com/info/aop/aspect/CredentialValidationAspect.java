package com.info.aop.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class CredentialValidationAspect {

    private final HttpServletRequest request;

    public CredentialValidationAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(com.info.aop.config.ValidateCredentials)")
    public void validateCredentialsPointcut() {}

    @Before("validateCredentialsPointcut()")
    public void validateCredentials(JoinPoint joinPoint) {
        // Extract headers from the HTTP request
        String apiKey = request.getHeader("X-API-KEY");
        String authToken = request.getHeader("Authorization");

        // Perform validation (this logic can be customized)
        if (apiKey == null || !apiKey.equals("VALID_API_KEY")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }

        if (authToken == null || !authToken.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Authorization Token");
        }

        // Extract Token (Bearer token example)
        String token = authToken.substring(7); // Remove "Bearer " prefix

        // Validate token (Can be replaced with JWT/OAuth validation logic)
        if (!isValidToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }

        System.out.println("✅ Credentials validated for method: " +
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }

    private boolean isValidToken(String token) {
        // Simulate token validation (Replace with real JWT/OAuth validation)
        return "VALID_TOKEN".equals(token);
    }
}
