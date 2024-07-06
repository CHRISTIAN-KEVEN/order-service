package com.example.orderService.dtos.requestDtos;

import lombok.*;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartRequest {
    private Long itemId;
    private int quantity;
    @Nullable
    private String discounts;
    private String businessId;
    private String userId; // Used for now. Eventually will be removed to use token

}
