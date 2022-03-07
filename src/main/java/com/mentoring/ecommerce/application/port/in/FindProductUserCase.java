package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;

import java.util.List;

public interface FindProductUserCase {

    List<Product> findAll();
}
