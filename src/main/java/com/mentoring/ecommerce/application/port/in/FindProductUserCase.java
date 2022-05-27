package com.mentoring.ecommerce.application.port.in;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.Product;

import java.util.List;

@UseCase
public interface FindProductUserCase {

    List<Product> findAll();

    Product findById(final Integer id);
}
