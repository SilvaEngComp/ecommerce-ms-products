package com.eliabe.ecommerce.products.application.ports.input;

import com.eliabe.ecommerce.products.domain.model.Product;

import java.math.BigDecimal;
import java.util.Optional;

@FunctionalInterface
public interface GetProductUseCase {
    Optional<Product> execute(Long code);
}
