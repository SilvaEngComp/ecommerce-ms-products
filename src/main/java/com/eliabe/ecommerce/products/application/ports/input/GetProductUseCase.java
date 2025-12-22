package com.eliabe.ecommerce.products.application.ports.input;

import com.eliabe.ecommerce.products.web.dto.ProductDTO;

import java.util.Optional;

@FunctionalInterface
public interface GetProductUseCase {
    Optional<ProductDTO> execute(Long code);
}
