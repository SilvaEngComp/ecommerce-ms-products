package com.eliabe.ecommerce.products.domain.output;

import com.eliabe.ecommerce.products.domain.model.Product;

@FunctionalInterface
public interface ProductOutputPort {
    Product save(Product product);
}
