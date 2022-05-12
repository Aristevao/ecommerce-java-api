package com.mentoring.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

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

//     Method to test AWS connection
    public List<String> listBuckets() {
        log.info("List buckets " + bucketName);
        return amazonS3Client().listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

//    @Value("${aws-access-key}")
//    private String AWS_ACCESS_KEY;
//
//    @Value("${aws-secret-key}")
//    private String AWS_SECRET_KEY;

//    private String AWS_ACCESS_KEY = "AKIAR6KAAIAQDN2MGXUQ";
//
//    private String AWS_SECRET_KEY = "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";
//
//    private final AmazonS3 amazonS3;
//
//    @Value("${s3.bucket-name}")
//    private String bucketName;
//
//    public S3Configuration() {
//        this.bucketName = "user-profile-photo-bkt";
//        var credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
//        amazonS3 = AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(Regions.US_EAST_1)
//                .build();
//    }
//
//
//    // Method to test AWS connection
//    public List<String> listBuckets() {
//        log.info("List buckets " + bucketName);
//
//        return amazonS3.listBuckets()
//                .stream()
//                .map(Bucket::getName)
//                .collect(Collectors.toList());
//    }
//
//    @SneakyThrows
//    public void upload(final String path, final InputStream inputStream, final String fileName) {
//        final byte[] bytes = IOUtils.toByteArray(inputStream);
//        upload(path, bytes, fileName);
//    }
//
//    @SneakyThrows
//    public void upload(final String path, final byte[] bytes, final String fileName) {
//        final String fullPath = bucketName.concat("/").concat(path);
//        log.info("Uploading file {} to S3 bucket {}", fileName, fullPath); // TODO log trough aspect
//
//        final ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(bytes.length);
//
//        final InputStream inputStream = new ByteArrayInputStream(bytes);
//        amazonS3.putObject(fullPath, fileName, inputStream, metadata);
//    }
}
