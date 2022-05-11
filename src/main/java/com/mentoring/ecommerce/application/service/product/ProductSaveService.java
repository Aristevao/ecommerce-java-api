package com.mentoring.ecommerce.application.service.product;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.S3HandlerPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    private final SaveProductPort savePort;
    private final S3HandlerPort uploadPort;
//    private final AmazonS3Adapter amazonS3Adapter;


    private final String image64 = "iVBORw0KGgoAAAANSUhEUgAAACIAAAAdCAYAAADPa766AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAA9SURBVFhH7dEhEQAADMPASSmff30dj4KBgOe5yyTpB4aQIWQIGUKGkCFkCBlChpAhZAjN7vYD15Ah9CQkPcFLTJQM3BQ8AAAAAElFTkSuQmCC";

    @Override
    public Product saveProduct(final Product product) {
//         if (product.getPath() != null) {
            byte[] photo = org.apache.commons.codec.binary.Base64.decodeBase64(image64);
            InputStream fis = new ByteArrayInputStream(photo);
            uploadPort.upload("product-photos", fis, "product_photo_".concat(product.getName())); // TODO Dynamize path and fileName
//        }
//         product.setPath(path) // TODO Learn how to get object path to save in the database
        uploadPort.listBuckets().forEach(System.out::println); // Method to test AWS connection

        return savePort.saveProduct(product);
    }
}
