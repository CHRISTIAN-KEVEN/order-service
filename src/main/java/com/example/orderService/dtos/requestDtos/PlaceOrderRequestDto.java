package com.example.orderService.dtos.requestDtos;

import com.example.orderService.utilities.Constants.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequestDto {
    private int itemCount;
    private String itemsToCheckout;
    private double orderTotal;
    private String businessId;
    private String ordererId;
    private String paymentMethod;
    private JSONObject paymentAccount;
    private String deliveryAddress;
    private String deliveryOption;

}
