package com.eliabe.ecommerce.products.domain.service;

import com.eliabe.ecommerce.products.application.ports.input.CreateProductUseCase;
import com.eliabe.ecommerce.products.application.ports.input.GetProductUseCase;
import com.eliabe.ecommerce.products.domain.model.Product;
import com.eliabe.ecommerce.products.domain.output.ProductOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {
    private final ProductOutputPort productOutputPort;

    @Override
    public Product execute(String name, BigDecimal unitPrice){
        // 1. Validation Logic
        if (unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return this.productOutputPort.save(new Product(null, name, unitPrice));
    }

    @Override
    public Optional<Product> execute(Long code) {
        return this.productOutputPort.findByCode(code);
    }
}
