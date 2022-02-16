package com.mentoring.ecommerce.application.service;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    private SaveProductPort getProduct;

    @Override public void saveProduct(final Product product) {
        getProduct.saveProduct(product);
    }
}
