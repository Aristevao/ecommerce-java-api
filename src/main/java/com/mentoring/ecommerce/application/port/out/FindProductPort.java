package com.mentoring.ecommerce.application.port.out;

import com.mentoring.ecommerce.domain.Product;

import java.util.List;

public interface FindProductPort {

    List<Product> findAll();
}
