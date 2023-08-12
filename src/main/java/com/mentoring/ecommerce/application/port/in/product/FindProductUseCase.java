package com.mentoring.ecommerce.application.port.in.product;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
public interface FindProductUseCase {

    Page<Product> findAll(Pageable pageable);

    Product findById(final Integer id);
}
