package com.eliabe.ecommerce.products.web.controller;

import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.domain.service.ProductService;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Validated
public class ProdutoController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductRequest request){
       ProductDTO response =  this.service.execute(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductDTO> findProduct(@RequestParam Long code){
        return this.service.execute(code)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
