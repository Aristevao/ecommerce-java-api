package com.mentoring.ecommerce.application.service.user.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class UserSaveServiceAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.application.service.user.UserSaveService";

    @Before("execution(* " + POINTCUT_PATH + ".save(..))")
    public void logSaveUserOngoing() {
        log.info("Registering user");
    }

    @After("execution(* " + POINTCUT_PATH + ".save(..))")
    public void logSaveUserSuccess() {
        log.info("Successfully registered user");
    }
}
