package com.eliabe.ecommerce.products.application.ports.input;

import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;

import java.util.List;

@FunctionalInterface
public interface FindAllProductUseCase {
    List<ProductDTO> execute();
}
