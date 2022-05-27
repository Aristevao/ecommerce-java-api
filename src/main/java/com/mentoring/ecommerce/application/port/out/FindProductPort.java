package com.mentoring.ecommerce.application.port.out;

import com.mentoring.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FindProductPort {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(final Integer id);
}
