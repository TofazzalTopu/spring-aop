package com.info.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // Annotation applicable to methods
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for AOP processing
public @interface ValidateCredentials {
}
