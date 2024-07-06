package com.example.orderService.repositories;

import com.example.orderService.models.TCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TCartItemRepository extends JpaRepository<TCartItem, Long> {
    List<TCartItem> findByLgId(@Param("lgId") List<Long> itemsToCheckout);
}