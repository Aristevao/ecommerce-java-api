package com.mentoring.ecommerce.application.service;


import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductFindService implements FindProductUserCase {

    @Autowired
    private FindProductPort port;

    @Override
    public List<Product> findAll() {
        return port.findAll();
    }

    @Override
    public Product findById(final Integer id) {
        return port.findById(id);
    }
}
