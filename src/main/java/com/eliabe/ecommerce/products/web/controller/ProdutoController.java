package com.eliabe.ecommerce.products.web.controller;

import com.eliabe.ecommerce.products.domain.model.Product;
import com.eliabe.ecommerce.products.domain.service.ProductService;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Validated
public class ProdutoController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductRequest request){
       Product response =  this.service.execute(request.getName(),request.getUnitPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> findProduct(@RequestParam Long code){
        return this.service.execute(code)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
