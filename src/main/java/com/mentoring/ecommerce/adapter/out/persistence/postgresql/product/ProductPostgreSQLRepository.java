package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductPostgreSQLRepository extends CrudRepository<Product, Integer> {
}
