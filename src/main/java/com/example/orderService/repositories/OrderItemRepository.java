package com.example.orderService.repositories;

import com.example.orderService.models.TOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<TOrderItem, Long> {
}
