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
public class ProductFindServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductFindService";

    @Before("execution(* " + POINTCUT_PATH + ".findAll(..))")
    public void logFindAllProductsOngoing() {
        log.info("Retrieving products");
    }

    @After("execution(* " + POINTCUT_PATH + ".findAll(..))")
    public void logFindAllProductsSuccess() {
        log.info("Successfully retrieved products");
    }

    @Before("execution(* " + POINTCUT_PATH + ".findById(..))")
    public void logFindProductByIdSuccess(final JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Retrieving product {}", args[0]);
    }

    @After("execution(* " + POINTCUT_PATH + ".findById(..))")
    public void logFindProductByIdSuccess() {
        log.info("Successfully retrieved product");
    }
}
