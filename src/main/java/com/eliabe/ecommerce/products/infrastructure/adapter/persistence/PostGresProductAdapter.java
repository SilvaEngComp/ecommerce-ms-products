package com.eliabe.ecommerce.products.infrastructure.adapter.persistence;

import com.eliabe.ecommerce.products.domain.output.ProductOutputPort;
import com.eliabe.ecommerce.products.domain.model.Product;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity.ProductEntity;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostGresProductAdapter implements ProductOutputPort {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product save(Product product){
        ProductEntity productEntity = new ProductEntity(product.code(),product.name(),product.unitPrice());
        return entityToDto(jpaProductRepository.save(productEntity));
    }

    @Override
    public Optional<Product> findByCode(Long code) {
        return Optional.ofNullable(entityToDto(jpaProductRepository.findById(code).orElse(null)));
    }

    private Product entityToDto(ProductEntity productEntity){
        if(productEntity==null){
            return null;
        }
        return new Product(productEntity.getCode(), productEntity.getName(), productEntity.getUnitPrice());
    }
}
