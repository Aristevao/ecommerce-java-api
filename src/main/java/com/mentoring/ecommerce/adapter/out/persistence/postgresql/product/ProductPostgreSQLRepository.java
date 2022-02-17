package com.mentoring.ecommerce.adapter.out.persistence.postgresql.product;

import com.mentoring.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostgreSQLRepository extends JpaRepository<Product, Integer> {
}
