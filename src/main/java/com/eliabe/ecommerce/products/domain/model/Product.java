package com.eliabe.ecommerce.products.domain.model;

import java.math.BigDecimal;

public record Product (
    
     Long code,

     String name,

     BigDecimal unitPrice
){}
