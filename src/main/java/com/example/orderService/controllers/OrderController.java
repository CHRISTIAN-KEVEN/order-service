package com.example.orderService.controllers;

import com.example.orderService.dtos.requestDtos.PlaceOrderRequestDto;
import com.example.orderService.services.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Log4j2
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody String request) {
        return orderService.addToCart(request);
    }

    @PostMapping("place-order")
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderRequestDto request) {
        return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.CREATED);
    }
}
