package com.mentoring.ecommerce.application.port.out;

import com.mentoring.ecommerce.domain.Product;

public interface UpdateProductPort {

    Product updateProduct(final Product product, final Integer id);
}
