package com.mentoring.ecommerce.application.service.product.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ProductUpdateServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductUpdateService";

    @Before("execution(* " + POINTCUT_PATH + ".update(..))")
    public void logUpdateProductOngoing(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Updating product {}", args[1]);
    }

    @After("execution(* " + POINTCUT_PATH + ".update(..))")
    public void logUpdateProductSuccess() {
        log.info("Successfully updated product");
    }
}
