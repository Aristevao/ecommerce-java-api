package com.mentoring.ecommerce.application.port.in;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.Product;

@UseCase
public interface UpdateProductUserCase {

    Product updateProduct(final Product product, final Integer id);
}
