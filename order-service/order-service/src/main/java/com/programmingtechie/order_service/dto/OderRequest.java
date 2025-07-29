package com.programmingtechie.order_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OderRequest {
   private List<OrderLineItemRequestList> orderLineItemsDtoList;
}
