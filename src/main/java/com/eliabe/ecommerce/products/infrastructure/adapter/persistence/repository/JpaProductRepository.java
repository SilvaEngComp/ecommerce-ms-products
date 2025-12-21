package com.eliabe.ecommerce.products.infrastructure.adapter.persistence.repository;

import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
