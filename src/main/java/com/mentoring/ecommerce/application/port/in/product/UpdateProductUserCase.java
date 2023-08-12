package com.mentoring.ecommerce.application.port.in.product;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.Product;

@UseCase
public interface UpdateProductUserCase {

    Product update(Product product, Integer id);
}
