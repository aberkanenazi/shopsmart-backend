package com.aberkane.shopsmart_backend.service.impl;

import com.aberkane.shopsmart_backend.model.Order;
import com.aberkane.shopsmart_backend.model.OrderItem;
import com.aberkane.shopsmart_backend.model.OrderStatus;
import com.aberkane.shopsmart_backend.model.PaymentStatus;
import com.aberkane.shopsmart_backend.repository.OrderRepository;
import com.aberkane.shopsmart_backend.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id)
                .map(orderRepository::save)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setOrderStatus(OrderStatus.valueOf(status));
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrderPaymentStatus(Long id, String paymentStatus) {
        return orderRepository.findById(id)
                .map(order -> {
                    try {
                        order.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Invalid payment status");
                    }
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository.findAllByUserUserId(userId);

    }

}
