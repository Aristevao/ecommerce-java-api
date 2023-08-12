package com.mentoring.ecommerce.application.port.out.aws;

import java.io.InputStream;
import java.util.List;

public interface S3HandlerPort {

    void upload(String path, InputStream inputStream, String fileName);

    List<String> listBuckets();
}
