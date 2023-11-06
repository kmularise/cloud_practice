package com.cloud.practice.global.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
	int value() default 20;
}
