package com.mentoring.ecommerce.application.service.product;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.S3HandlerPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    private final SaveProductPort savePort;
    private final S3HandlerPort uploadPort;

    @Override
    public Product saveProduct(final Product product) {
         if (product.getPath() != null) {
            byte[] photo = decodeBase64(product.getPath());
            InputStream fis = new ByteArrayInputStream(photo);
            uploadPort.upload("product-photos", fis, "product_photo_".concat(product.getName())); // TODO Dynamize path and fileName
        }
//         product.setPath(path) // TODO Learn how to get object path to save in the database


        product.setPath(null);
        return savePort.saveProduct(product);
    }
}
