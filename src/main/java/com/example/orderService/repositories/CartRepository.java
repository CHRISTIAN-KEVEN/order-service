package com.example.orderService.repositories;

import com.example.orderService.models.TCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<TCart, Long> {

    TCart findByStrUserId(@Param("strUserId") String userId);
}