package com.example.orderService.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductService {

    @GetMapping("products/{id}")
    public String getProductDetails(@PathVariable("id") Long itemId);
}
