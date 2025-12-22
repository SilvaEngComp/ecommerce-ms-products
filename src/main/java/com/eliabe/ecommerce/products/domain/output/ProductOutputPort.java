package com.eliabe.ecommerce.products.domain.output;

import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;

import java.util.Optional;

public interface ProductOutputPort {
    ProductDTO save(ProductRequest request);

    Optional<ProductDTO> findByCode(Long code);
}
