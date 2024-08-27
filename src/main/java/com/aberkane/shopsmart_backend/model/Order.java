package com.aberkane.shopsmart_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "stripe_payment_id", nullable = false)
    private String stripePaymentId;
    @Column(name = "stripe_charge_id", nullable = false)
    private String stripeChargeId;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @Column(name = "shipping_address", nullable = false, columnDefinition = "TEXT")
    private String shippingAddress;
    @Column(name = "shipping_country", nullable = false)
    private String shippingCountry;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", currency='" + currency + '\'' +
                ", stripePaymentId='" + stripePaymentId + '\'' +
                ", stripeChargeId='" + stripeChargeId + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", shippingCountry='" + shippingCountry + '\'' +
                ", orderStatus=" + orderStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
