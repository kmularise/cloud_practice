package com.cloud.practice.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cloud.practice.global.aop.Retry;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Aspect
@Component
public class RetryAspect {
	private static final int MAX_RETRIES = 1000;
	private static final int RETRY_DELAY_MS = 100;

	@Around("@annotation(retry)")
	public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
		int maxRetry = retry.value();
		Exception exceptionHolder = null;
		for (int retryCount = 0; retryCount < MAX_RETRIES ; retryCount++) {
			try {
				return joinPoint.proceed();
			} catch (Exception e) {
				log.warn("[retry] try count = {}/{}", retryCount, maxRetry);
				exceptionHolder = e;
				Thread.sleep(RETRY_DELAY_MS);
			}
		}
		throw exceptionHolder;
	}
}
