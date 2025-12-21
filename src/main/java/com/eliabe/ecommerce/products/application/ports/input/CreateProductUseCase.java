package com.eliabe.ecommerce.products.application.ports.input;

import com.eliabe.ecommerce.products.domain.model.Product;

import java.math.BigDecimal;

@FunctionalInterface
public interface CreateProductUseCase {
    Product execute(String name, BigDecimal unitPrice);
}
