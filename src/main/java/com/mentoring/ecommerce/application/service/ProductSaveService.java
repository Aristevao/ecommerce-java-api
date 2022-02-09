package com.mentoring.ecommerce.application.service;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSaveService implements SaveProductUseCase {

    private SaveProductPort getProduct;

    @Override public Product saveProduct(final Product product) {
        return getProduct.saveProduct(product);
    }
}
