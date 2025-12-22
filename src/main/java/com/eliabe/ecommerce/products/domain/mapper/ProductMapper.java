package com.eliabe.ecommerce.products.domain.mapper;

import com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity.ProductEntity;
import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(ProductEntity entity);
    ProductEntity toEntity(ProductRequest request);

}
