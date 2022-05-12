package com.mentoring.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class S3Configuration {

    // TODO Tentar usar código abaixo para S3Configuration para tentar evitar erro de @bean nessa classe Adapter (em branch separada)
    private static String AWS_ACCESS_KEY = "access-key";
    private static String AWS_SECRET_KEY = "access-key";

//    @Value("${aws-access-key}")
//    private String AWS_ACCESS_KEY;
//
//    @Value("${aws-secret-key}")
//    private String AWS_SECRET_KEY;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Bean
    public static AmazonS3Client amazonS3Client() {
        var credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
