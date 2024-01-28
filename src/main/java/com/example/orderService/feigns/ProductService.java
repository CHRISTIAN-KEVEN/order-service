package com.example.orderService.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product-service")
public interface ProductService {

    @GetMapping("products/{id}")
    public String getProductDetails(@PathVariable("id") Long itemId);

    @PostMapping("products/update-item-stock")
    public String updateProductStock(@RequestBody String request);
}
