package com.mentoring.ecommerce.application.service;

import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import com.mentoring.ecommerce.application.port.out.UpdateProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductUpdateService implements UpdateProductUserCase {

    UpdateProductPort getProduct;

    ProductFindService productFindService;

    ProductSaveService productSaveService;

    @Override
    public void updateProduct(final Product product, final Integer id) {
        final Optional<Product> oldProduct = productFindService.findById(id);
        oldProduct.orElseThrow(ProductNotFoundException::new);
        product.setId(id);
        productSaveService.saveProduct(product);
    }
}
