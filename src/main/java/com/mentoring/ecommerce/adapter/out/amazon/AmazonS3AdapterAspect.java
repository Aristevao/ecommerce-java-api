package com.mentoring.ecommerce.adapter.out.amazon;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AmazonS3AdapterAspect {

    private final static String POINTCUT_PATH = "com.mentoring.ecommerce.adapter.out.amazon.AmazonS3Adapter";

    @Before("execution(* " + POINTCUT_PATH + ".upload(..))")
    public void logUploadOngoing(final JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        log.info("Uploading file {} to S3 bucket {}", args[0], args[1]);
    }

    @After("execution(* " + POINTCUT_PATH + ".upload(..))")
    public void logUploadSuccess() {
        log.info("Successfully uploaded file to S3 bucket");
    }

    @Before("execution(* " + POINTCUT_PATH + ".listBuckets(..))")
    public void logListBucketsOngoing(final JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        log.info("Listing S3 buckets");
    }

    @After("execution(* " + POINTCUT_PATH + ".listBuckets(..))")
    public void logListBucketsSuccess() {
        log.info("Successfully listed S3 buckets");
    }
}
