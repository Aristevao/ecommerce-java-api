package com.mentoring.ecommerce.application.port.in.product;

import com.mentoring.common.annotaion.UseCase;

@UseCase
public interface DeleteProductUseCase {

    void delete(final Integer id);
}
