package com.eliabe.ecommerce.products.infrastructure.adapter.persistence;

import com.eliabe.ecommerce.products.domain.output.ProductOutputPort;
import com.eliabe.ecommerce.products.domain.model.Product;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity.ProductEntity;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostGresProductAdapter implements ProductOutputPort {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product save(Product product){
        ProductEntity productEntity = new ProductEntity(product.code(),product.name(),product.unitPrice());
        ProductEntity savedEntity = jpaProductRepository.save(productEntity);
        return new Product(savedEntity.code(), savedEntity.name(), savedEntity.unitPrice());
    }
}
