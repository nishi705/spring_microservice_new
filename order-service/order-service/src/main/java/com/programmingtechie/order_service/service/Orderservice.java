package com.programmingtechie.order_service.service;

import com.programmingtechie.order_service.dto.InventoryResponse;
import com.programmingtechie.order_service.dto.OderRequest;
import com.programmingtechie.order_service.dto.OrderLineItemRequestList;
import com.programmingtechie.order_service.event.OrderPlacedEvent;
import com.programmingtechie.order_service.model.Order;
import com.programmingtechie.order_service.model.OrderLineItem;
import com.programmingtechie.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class Orderservice {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OderRequest oderRequest) throws IllegalAccessException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItem = oderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToObj).toList();
        order.setOrderLineItemList(orderLineItem);

        //fetch skuCode from the orderItems
        List<String> skuCodes = order.getOrderLineItemList().stream()
                .map(OrderLineItem::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                        .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder
                                .queryParam("skuCode",skuCodes)
                                .build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                                .block();
        boolean allProductInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(allProductInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            return "order placed successfully";
        }else{
            throw new IllegalAccessException("product not found in the stock cannot place the order");
        }

    }

    private OrderLineItem mapToObj(OrderLineItemRequestList orderLineItemRequestList) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemRequestList.getPrice());
        orderLineItem.setQuantity(orderLineItemRequestList.getQuantity());
        orderLineItem.setSkuCode(orderLineItemRequestList.getSkuNumber());
        return orderLineItem;


    }

}