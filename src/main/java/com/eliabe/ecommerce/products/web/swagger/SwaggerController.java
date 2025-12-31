package com.eliabe.ecommerce.products.web.swagger;


import com.eliabe.ecommerce.products.web.dto.ProductDTO;
import com.eliabe.ecommerce.products.web.dto.ProductRequest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation with success"),
        @ApiResponse(responseCode = "400", description = "Invalid parameter"),
})
public interface SwaggerController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created with success"),
    })


    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductRequest request);

    @GetMapping("/{code}")
    public ResponseEntity<ProductDTO> findByCode(@PathVariable Long code);


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProduct();
}
