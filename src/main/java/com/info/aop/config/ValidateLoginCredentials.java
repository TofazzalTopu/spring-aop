package com.info.aop.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // Annotation applicable to methods
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for AOP processing
public @interface ValidateLoginCredentials {
}
