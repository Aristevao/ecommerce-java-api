package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;

import java.util.List;
import java.util.Optional;

public interface FindProductUserCase {

    List<Product> findAll();

    Optional<Product> findById(final Integer id);
}
