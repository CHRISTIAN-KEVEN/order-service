package com.example.orderService.dtos.requestDtos;

import com.example.orderService.utilities.Constants.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequestDto {
    private int quantity;
    private long itemId;
    private double orderTotal;
    private PaymentMethod paymentMethod;
}
