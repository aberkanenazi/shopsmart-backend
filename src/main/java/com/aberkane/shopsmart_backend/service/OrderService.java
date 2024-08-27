package com.aberkane.shopsmart_backend.service;

import com.aberkane.shopsmart_backend.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    void deleteOrder(Long id);
    Order updateOrder(Long id, Order order);
    Order updateOrderStatus(Long id, String status);
    Order updateOrderPaymentStatus(Long id, String paymentStatus);
}
