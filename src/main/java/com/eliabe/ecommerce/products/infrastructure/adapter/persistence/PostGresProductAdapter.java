package com.eliabe.ecommerce.products.infrastructure.adapter.persistence;

import com.eliabe.ecommerce.products.domain.mapper.ProductMapper;
import com.eliabe.ecommerce.products.domain.output.ProductOutputPort;
import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity.ProductEntity;
import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.repository.JpaProductRepository;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostGresProductAdapter implements ProductOutputPort {
    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper mapper;

    @Override
    public ProductDTO save(ProductRequest request){
return Optional.of(request)
        .map(mapper::toEntity)
        .map(jpaProductRepository::save)
        .map(mapper::toDto)
        .orElseThrow();
    }

    @Override
    public Optional<ProductDTO> findByCode(Long code) {
        return jpaProductRepository.findById(code).map(mapper::toDto);
    }

}
