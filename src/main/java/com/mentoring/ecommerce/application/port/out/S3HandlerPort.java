package com.mentoring.ecommerce.application.port.out;

import java.io.InputStream;
import java.util.List;

public interface S3HandlerPort {

    void upload(final String path, final InputStream inputStream, final String fileName);

    List<String> listBuckets();
}
