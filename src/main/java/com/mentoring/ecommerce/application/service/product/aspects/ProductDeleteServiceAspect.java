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
public class ProductDeleteServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductDeleteService";

    @Before("execution(* " + POINTCUT_PATH + ".deleteProduct(..))")
    public void logDeleteProductOngoing(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Deleting product {}", args[0]);
    }

    @After("execution(* " + POINTCUT_PATH + ".deleteProduct(..))")
    public void logDeleteProductSuccess() {
        log.info("Successfully deleted product");
    }
}
