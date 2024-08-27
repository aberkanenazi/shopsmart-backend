package com.aberkane.shopsmart_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.aberkane.shopsmart_backend.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.aberkane.shopsmart_backend.model.User;
import com.aberkane.shopsmart_backend.model.dto.OrderDto;
import com.aberkane.shopsmart_backend.model.dto.UserDto;
import com.aberkane.shopsmart_backend.repository.OrderRepository;
import com.aberkane.shopsmart_backend.repository.UserRepository;
import com.aberkane.shopsmart_backend.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUserProfile(Long userId, User userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setPostalCode(userDto.getPostalCode());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAllByUserUserId(userId);
    }
}
