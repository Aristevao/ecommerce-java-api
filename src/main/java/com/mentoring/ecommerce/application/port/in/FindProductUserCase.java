package com.mentoring.ecommerce.application.port.in;

import com.mentoring.ecommerce.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FindProductUserCase {

    List<Product> findAll();

    Product findById(final Integer id);
}
