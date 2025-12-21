package com.eliabe.ecommerce.products.domain.output;

import com.eliabe.ecommerce.products.domain.model.Product;

import java.util.Optional;

public interface ProductOutputPort {
    Product save(Product product);

    Optional<Product> findByCode(Long code);
}
