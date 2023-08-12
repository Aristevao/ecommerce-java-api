package com.mentoring.ecommerce.application.service.product.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ProductSaveServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductSaveService";

    @Before("execution(* " + POINTCUT_PATH + ".save(..))")
    public void logSaveProductOngoing() {
        log.info("Registering product");
    }

    @After("execution(* " + POINTCUT_PATH + ".save(..))")
    public void logSaveProductSuccess() {
        log.info("Successfully registered product");
    }
}
