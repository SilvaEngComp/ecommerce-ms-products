package com.eliabe.ecommerce.products.application.ports.input;

import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;

@FunctionalInterface
public interface CreateProductUseCase {
    ProductDTO execute(ProductRequest request);
}
