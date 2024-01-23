package com.example.orderService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lgOrderId;

    @Column(name = "lg_item_id")
    private long lgItemId;
    @Column(name = "int_qty")
    private int intQty;
    @Column(name = "str_status")
    private String strStatus;

    @Column(name = "dt_order_date")
    private Instant dtOrderDate;

    @Column(name = "db_total_amount")
    private double dbTotalAmount;

//    @OneToMany(mappedBy = "lg_order_id")
//    private List<TOrderItem> orderItems;
}
//