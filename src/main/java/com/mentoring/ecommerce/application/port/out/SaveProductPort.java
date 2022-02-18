package com.mentoring.ecommerce.application.port.out;

import com.mentoring.ecommerce.domain.Product;

public interface SaveProductPort {

    void saveProduct(final Product product);
}
