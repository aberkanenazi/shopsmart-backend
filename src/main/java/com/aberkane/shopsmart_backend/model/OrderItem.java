package com.aberkane.shopsmart_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long orderItemId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "item_price", nullable = false)
    private Double itemPrice;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
}
