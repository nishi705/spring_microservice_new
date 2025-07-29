package com.programmingtechie.order_service.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemRequestList {
    private Long id;
    private String skuNumber;
    private BigDecimal price;
    private Integer quantity;
}
