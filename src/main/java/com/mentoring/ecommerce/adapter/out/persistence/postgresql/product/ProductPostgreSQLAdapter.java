package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.common.annotaion.PersistenceAdapter;
import com.mentoring.ecommerce.application.port.out.DeleteProductPort;
import com.mentoring.ecommerce.application.port.out.FindProductPort;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class ProductPostgreSQLAdapter implements FindProductPort, SaveProductPort, DeleteProductPort {

    private final ProductPostgreSQLRepository repository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(final Integer id) {
        return repository.findById(id);
    }

    @Override
    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(final Integer id) {
        repository.deleteById(id);
    }
}