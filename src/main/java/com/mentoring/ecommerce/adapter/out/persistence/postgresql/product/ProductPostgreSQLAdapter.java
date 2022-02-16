package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
// @PersistenceAdaper
public class ProductPostgreSQLAdapter implements FindProductPort, SaveProductPort {

    ProductPostgreSQLRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = repository.findById(id);
        product.orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        return product.get();
    }

    @Override
    public void saveProduct(final Product product) {
        repository.save(product);
    }
}