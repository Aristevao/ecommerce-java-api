package com.mentoring.ecommerce.application.service.product.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ProductUpdateServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.product.ProductUpdateService";

    @After("execution(* " + POINTCUT_PATH + ".updateProduct(..))")
    public void logUpdateProductSuccess() {
        log.info("Successfully updated product.");
    }
}
