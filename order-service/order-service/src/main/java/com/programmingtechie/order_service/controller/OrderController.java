package com.programmingtechie.order_service.controller;

import com.programmingtechie.order_service.dto.OderRequest;
import com.programmingtechie.order_service.service.Orderservice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final Orderservice orderservice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OderRequest orderRequest) throws IllegalAccessException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return orderservice.placeOrder(orderRequest);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

/*
supplyAsync() expects a return value. If orderservice.placeOrder(orderRequest)
returns void, this is invalid.
So, in place of supplyAsunc use runAsync()
 */
    }

    public CompletableFuture<String> fallbackMethod(OderRequest oderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() ->"oopss something went wrong please order after sometime");
    }

}
/*
timeout: so we are here implementing the timeout exception, if the order service will make a call
and net get the response even after 5 second also then timeout will throw an timeout exception
 */