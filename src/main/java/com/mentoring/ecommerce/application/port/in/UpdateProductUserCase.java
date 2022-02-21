package com.mentoring.ecommerce.application.port.in;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.Product;

@UseCase
public interface UpdateProductUserCase {

    void updateProduct(final Product product, final Integer id);
}
