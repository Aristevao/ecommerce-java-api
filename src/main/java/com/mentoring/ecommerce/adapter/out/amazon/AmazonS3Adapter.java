package com.mentoring.ecommerce.adapter.out.amazon;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import com.mentoring.ecommerce.application.port.out.S3HandlerPort;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.mentoring.configuration.S3Configuration.amazonS3Client;

@Component
@Slf4j
public class AmazonS3Adapter implements S3HandlerPort {

    @Value("${s3.bucket-name}")
    private String bucketName;

    public List<String> listBuckets() {
        log.info("List buckets");
        return amazonS3Client().listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

        @SneakyThrows
    public void upload(final String path, final InputStream inputStream, final String fileName) {
        final byte[] bytes = IOUtils.toByteArray(inputStream);
        upload(path, bytes, fileName);
    }

    @SneakyThrows
    public void upload(final String path, final byte[] bytes, final String fileName) {
        final String fullPath = bucketName.concat("/").concat(path);
        log.info("Uploading file {} to S3 bucket {}", fileName, fullPath);

        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        final InputStream inputStream = new ByteArrayInputStream(bytes);
        amazonS3Client().putObject(fullPath, fileName, inputStream, metadata);
    }
//
//    // TODO Tentar usar código abaixo para S3Configuration para tentar evitar erro de @bean nessa classe Adapter (em branch separada)
//    private static String AWS_ACCESS_KEY = "AKIAR6KAAIAQDN2MGXUQ";
//
//    private static String AWS_SECRET_KEY = "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";
//
//    @Bean
//    public static AmazonS3Client amazonS3Client() {
//        var credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
//        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//    }
}

//@AllArgsConstructor
//@Component
//@Slf4j
//public class AmazonS3Adapter implements UploadPort {
//
//    private final AmazonS3Client amazonS3Client;
//
//    @Value("${s3.bucket-name}")
//    private final String bucketName;
//
////    @SneakyThrows
////    public void upload(final String path, final InputStream inputStream, final String fileName) {
////        final byte[] bytes = IOUtils.toByteArray(inputStream);
////        upload(path, bytes, fileName);
////    }
////
////    @SneakyThrows
////    public void upload(final String path, final byte[] bytes, final String fileName) {
////        final String fullPath = bucketName.concat("/").concat(path);
////        log.info("Uploading file {} to S3 bucket {}", fileName, fullPath);
////
////        final ObjectMetadata metadata = new ObjectMetadata();
////        metadata.setContentLength(bytes.length);
////
////        final InputStream inputStream = new ByteArrayInputStream(bytes);
////        amazonS3Client.putObject(fullPath, fileName, inputStream, metadata);
////    }
//}