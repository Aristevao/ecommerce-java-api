package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.common.exceptions.ProductNotFoundException;
import com.mentoring.ecommerce.application.port.out.DeleteProductPort;
import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductPostgreSQLAdapter implements FindProductPort, SaveProductPort, DeleteProductPort {

    @Autowired
    ProductPostgreSQLRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(final Integer id) {
        Optional<Product> product = repository.findById(id);
        product.orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        return product.get();
    }

    @Override
    public void saveProduct(final Product product) {
        repository.save(product);
    }

    @Override
    public void deleteProduct(final Integer id) {
        repository.deleteById(id);
    }
}