package com.example.orderService.repositories;

import com.example.orderService.models.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<TOrder, Long> {

}
