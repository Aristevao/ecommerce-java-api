package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;

public interface SaveProductUseCase {

    void saveProduct(final Product product);
}
