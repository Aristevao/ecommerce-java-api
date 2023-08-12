package com.mentoring.ecommerce.application.service.product;


import com.mentoring.common.exceptions.NotFoundException;
import com.mentoring.common.pagination.PageBuilder;
import com.mentoring.ecommerce.application.port.in.product.FindProductUserCase;
import com.mentoring.ecommerce.application.port.out.product.FindProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductFindService implements FindProductUserCase {

    private final FindProductPort port;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return port.findAll(new PageBuilder().setPage(pageable.getPageNumber()).build());
    }

    @Override
    public Product findById(final Integer id) {
        final Optional<Product> product = port.findById(id);
        product.orElseThrow(() -> new NotFoundException("Product not found: " + id));
        return product.get();
    }
}
