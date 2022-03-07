package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;

public interface UpdateProductUserCase {

    void updateProduct(final Product product, final Integer id);
}
