package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.application.port.out.UpdateProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
// @PersistenceAdaper
public class ProductPostgreSQLAdapter implements FindProductPort, UpdateProductPort, SaveProductPort {

    ProductPostgreSQLRepository repository;

    @Override public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override public void saveProduct(final Product product) {
        repository.save(product);
    }

    @Override public Product updateProduct(final Product product, final Integer id) {
        return repository.save(product);
    }
}