package com.programmingtechie.order_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "t_orderlineitem")
public class OrderLineItem {
//A SKU, or Stock Keeping Unit, is a unique alphanumeric
// code assigned to a product for internal inventory management
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
