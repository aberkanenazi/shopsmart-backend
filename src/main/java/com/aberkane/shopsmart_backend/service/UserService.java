package com.aberkane.shopsmart_backend.service;

import java.util.List;

import com.aberkane.shopsmart_backend.model.Order;
import com.aberkane.shopsmart_backend.model.User;

public interface UserService {
    User getUserProfile(Long userId);
    void updateUserProfile(Long userId, User userDto);
    List<Order> getUserOrders(Long userId);

}
