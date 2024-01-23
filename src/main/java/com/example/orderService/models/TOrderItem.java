package com.example.orderService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lg_id")
    private long lgId;

//    @ManyToOne()
    @Column(name = "lg_order_id")
    private long lgOrderId;

    @Column(name = "lg_item_id")
    private long lgItemId;
}
