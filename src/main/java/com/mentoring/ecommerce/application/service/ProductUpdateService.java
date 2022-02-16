package com.mentoring.ecommerce.application.service;

import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import com.mentoring.ecommerce.application.port.out.UpdateProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductUpdateService implements UpdateProductUserCase {

    UpdateProductPort getProduct;

    ProductFindService productFindService;

    ProductSaveService productSaveService;

    @Override
    public void updateProduct(final Product product, final Integer id) {
        final Product oldProduct = productFindService.findById(id);
        if (oldProduct == null) {
            throw new ProductNotFoundException();
        }
        product.setId(id);
        productSaveService.saveProduct(product);
    }
}
