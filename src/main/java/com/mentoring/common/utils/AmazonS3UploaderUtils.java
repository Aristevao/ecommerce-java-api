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

    private final String filePathName = "product-photos";
    private String fileName = String.valueOf(UUID.randomUUID());
    private final String bucketName = "product-photo-bkt";

    @Bean
    public InputStream base64ToInputStream(String fileBase64) {
        byte[] photo = decodeBase64(fileBase64);
        return new ByteArrayInputStream(photo);
    }

    @Bean
    public String getFileFullPath(String fileName) {
        String extension;
        if (fileName != null) {
            String[] splitFileName = fileName.split("[.]", 0);
            extension = splitFileName[splitFileName.length - 1];
            this.fileName = this.fileName.concat(".").concat(extension);
        }
        return bucketName.concat("/").concat(filePathName).concat("/").concat(this.fileName);
    }
}
