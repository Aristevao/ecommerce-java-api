package com.mentoring.ecommerce.application.service;

import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductUpdateService implements UpdateProductUserCase {

    @Autowired
    ProductFindService productFindService;

    @Autowired
    ProductSaveService productSaveService;

    @Override
    public void updateProduct(final Product product, final Integer id) {
        productFindService.findById(id);
        product.setId(id);
        productSaveService.saveProduct(product);
    }
}
