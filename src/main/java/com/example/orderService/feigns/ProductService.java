package com.example.orderService.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("product-service")
public interface ProductService {

    @GetMapping("products/{id}")
    public String getProductDetails(@PathVariable("id") Long itemId);

    @GetMapping("products/")
    public String getProductsDetails(@RequestParam String productIds);

    @PostMapping("products/update-item-stock")
    public String updateProductStock(@RequestBody String request);
    @PostMapping("products/update-items-stock")
    public String updateItemsStock(@RequestBody String request);
}
