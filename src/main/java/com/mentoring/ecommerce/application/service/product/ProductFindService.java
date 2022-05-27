package com.mentoring.ecommerce.application.service.product;


import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductFindService implements FindProductUserCase {

    private final FindProductPort port;

    @Override
    public List<Product> findAll() {
        return port.findAll();
    }

    @Override
    public Product findById(final Integer id) {
        Optional<Product> product = port.findById(id);
        product.orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        return product.get();
    }
}
