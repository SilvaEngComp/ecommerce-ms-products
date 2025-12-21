package com.eliabe.ecommerce.products.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public record ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long code,

    @Column
    String name,

    @Column(name = "unit_price", nullable = false, precision = 16, scale = 2)
    BigDecimal unitPrice
){


}
