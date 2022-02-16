package com.mentoring.ecommerce.application.port.out;

import com.mentoring.ecommerce.domain.Product;

import java.util.List;
import java.util.Optional;

public interface FindProductPort {

    List<Product> findAll();

    Optional<Product> findById(final Integer id);
}
