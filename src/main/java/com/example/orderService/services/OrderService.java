package com.example.orderService.services;

import com.example.orderService.dtos.requestDtos.PlaceOrderRequestDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<String> addToCart(String request);

    String placeOrder(PlaceOrderRequestDto request);
}
