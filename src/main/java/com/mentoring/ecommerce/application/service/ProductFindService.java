package com.mentoring.ecommerce.application.service;


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

    private FindProductPort port;

    @Override
    public List<Product> findAll() {
        return port.findAll();
    }

    @Override
    public Optional<Product> findById(final Integer id) {
        return port.findById(id);
    }
}
