package com.mentoring.ecommerce.application.service.product;


import com.mentoring.common.utils.AmazonS3UploaderUtils;
import com.mentoring.ecommerce.application.port.in.product.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.S3HandlerPort;
import com.mentoring.ecommerce.application.port.out.product.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    private final S3HandlerPort uploadPort;
    private final SaveProductPort savePort;

    @Override
    public Product saveProduct(final Product product) {
        String fullPath = null;
        if (product.getFilePath() != null) {
            final AmazonS3UploaderUtils s3Utils = new AmazonS3UploaderUtils();
            fullPath = s3Utils.getFileFullPath(product.getFileName());
            final InputStream fis = s3Utils.base64ToInputStream(product.getFilePath());
            uploadPort.upload(s3Utils.getFilePathName(), fis, s3Utils.getFileName());
        }
        product.setFilePath(fullPath);
        return savePort.saveProduct(product);
    }
}
