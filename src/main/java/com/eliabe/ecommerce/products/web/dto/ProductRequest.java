package com.eliabe.ecommerce.products.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
@Data
public class ProductRequest {
    @NotBlank(message = "The product name can not be blank")
    String name;
    @NotNull(message = "The product price can not be null")
    @Positive(message = "The product price can not be negative")
    BigDecimal unitPrice;
}
