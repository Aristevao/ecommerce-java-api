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
public class UserUpdateServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.user.UserUpdateService";

    @Before("execution(* " + POINTCUT_PATH + ".update(..))")
    public void logUpdateUserOngoing(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Updating user {}", args[1]);
    }

    @After("execution(* " + POINTCUT_PATH + ".update(..))")
    public void logUpdateUserSuccess() {
        log.info("Successfully updated user");
    }
}
