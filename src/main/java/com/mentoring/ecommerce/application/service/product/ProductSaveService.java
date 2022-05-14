package com.mentoring.ecommerce.application.service.product;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.S3HandlerPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    private final S3HandlerPort uploadPort;
    private final SaveProductPort savePort;
    @Value("${s3.bucket-name}")
    private String bucketName;

    @Override
    public Product saveProduct(final Product product) {
        String fullPath = null;
        if (product.getPath() != null) {
            byte[] photo = decodeBase64(product.getPath());
            InputStream fis = new ByteArrayInputStream(photo);
            String filePath = "product-photos";
            String fileName = String.valueOf(UUID.randomUUID());
            uploadPort.upload(filePath, fis, fileName);
            fullPath = bucketName.concat("/").concat(filePath).concat("/").concat(fileName);
        }
        product.setPath(fullPath);
        return savePort.saveProduct(product);
    }
}
