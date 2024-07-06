package com.example.orderService.services;

import com.example.orderService.dtos.requestDtos.AddToCartRequest;
import com.example.orderService.dtos.requestDtos.PlaceOrderRequestDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    String addToCart(AddToCartRequest request);

    String placeOrder(PlaceOrderRequestDto request);
}
