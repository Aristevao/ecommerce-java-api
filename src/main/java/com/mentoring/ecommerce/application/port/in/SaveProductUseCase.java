package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;

public interface SaveProductUseCase {

    Product saveProduct(final Product product);


}
