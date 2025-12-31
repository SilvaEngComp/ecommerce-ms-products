package com.eliabe.ecommerce.products.domain.service;

import com.eliabe.ecommerce.products.application.ports.input.CreateProductUseCase;
import com.eliabe.ecommerce.products.application.ports.input.FindAllProductUseCase;
import com.eliabe.ecommerce.products.application.ports.input.GetProductUseCase;
import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.application.ports.output.ProductOutputPort;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase, FindAllProductUseCase {
    private final ProductOutputPort productOutputPort;


    @Override
    public ProductDTO execute(ProductRequest request){

        return this.productOutputPort.save(request);
    }

    @Override
    public Optional<ProductDTO> execute(Long code) {
        return this.productOutputPort.findByCode(code);
    }

    @Override
    public List<ProductDTO> execute() {
        return productOutputPort.findAll();
    }
}
