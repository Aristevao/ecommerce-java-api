package com.mentoring.ecommerce.application.service.user.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class UserDeleteServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.user.UserDeleteService";

    @Before("execution(* " + POINTCUT_PATH + ".delete(..))")
    public void logDeleteUserOngoing(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Deleting product {}", args[0]);
    }

    @After("execution(* " + POINTCUT_PATH + ".delete(..))")
    public void logDeleteUserSuccess() {
        log.info("Successfully deleted product");
    }
}
