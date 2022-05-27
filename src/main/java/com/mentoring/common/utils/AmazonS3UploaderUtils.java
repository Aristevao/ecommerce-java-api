package com.mentoring.common.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

@Getter
@RequiredArgsConstructor
@Component
public class AmazonS3UploaderUtils {

    private final String FilePathName = "product-photos";
    private final String FileName = String.valueOf(UUID.randomUUID());
    private String bucketName = "product-photo-bkt";

    @Bean
    public InputStream base64ToInputStream(String fileBase64) {
        byte[] photo = decodeBase64(fileBase64);
        return new ByteArrayInputStream(photo);
    }

    @Bean
    public String getFileFullPath() {
        return bucketName.concat("/").concat(FilePathName).concat("/").concat(FileName);
    }
}
