package com.eliabe.ecommerce.products.repository;

import com.eliabe.ecommerce.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
