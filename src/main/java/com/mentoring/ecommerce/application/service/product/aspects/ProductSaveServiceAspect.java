package com.mentoring.ecommerce.application.service.product.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ProductSaveServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductSaveService";

    @After("execution(* " + POINTCUT_PATH + ".saveProduct(..))")
    public void logSaveProductSuccess() {
        log.info("Successfully registered product.");
    }
}
