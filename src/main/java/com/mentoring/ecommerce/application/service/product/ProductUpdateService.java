package com.mentoring.ecommerce.application.service.product;

import com.mentoring.ecommerce.application.port.in.product.UpdateProductUseCase;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductUpdateService implements UpdateProductUseCase {

    private final ProductFindService productFindService;

    private final ProductSaveService productSaveService;

    @Override
    public Product update(Product product, Integer id) {
        productFindService.findById(id);
        product.setId(id);
        return productSaveService.save(product);
    }
}
