package com.mentoring.ecommerce.adapter.out.amazon;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import com.mentoring.ecommerce.application.port.out.aws.S3HandlerPort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.mentoring.configuration.S3Configuration.amazonS3Client;

@Component
public class AmazonS3Adapter implements S3HandlerPort {

    @Value("${s3.bucket-name}")
    private String bucketName;

    public List<String> listBuckets() {
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

        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        final InputStream inputStream = new ByteArrayInputStream(bytes);
        amazonS3Client().putObject(fullPath, fileName, inputStream, metadata);
    }
}