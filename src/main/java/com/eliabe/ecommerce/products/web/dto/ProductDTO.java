package com.eliabe.ecommerce.products.web.dto;

import java.math.BigDecimal;

public record ProductDTO(
    
     Long code,

     String name,

     BigDecimal unitPrice
){}
