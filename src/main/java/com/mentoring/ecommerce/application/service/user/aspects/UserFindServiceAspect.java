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
public class UserFindServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.user.UserFindService";

    @Before("execution(* " + POINTCUT_PATH + ".findAll(..))")
    public void logFindAllUsersOngoing() {
        log.info("Retrieving users");
    }

    @After("execution(* " + POINTCUT_PATH + ".findAll(..))")
    public void logFindAllUsersSuccess() {
        log.info("Successfully retrieved users");
    }

    @Before("execution(* " + POINTCUT_PATH + ".findById(..))")
    public void logFindUserByIdSuccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Retrieving user {}", args[0]);
    }

    @After("execution(* " + POINTCUT_PATH + ".findById(..))")
    public void logFindUserByIdSuccess() {
        log.info("Successfully retrieved user");
    }
}
