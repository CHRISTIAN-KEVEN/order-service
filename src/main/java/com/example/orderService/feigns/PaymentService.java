package com.example.orderService.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-service")
public interface PaymentService {
    @PostMapping("initiate-payment")
    public String initiatePayment(@RequestBody String request);
}
