package com.aberkane.shopsmart_backend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aberkane.shopsmart_backend.model.Order;
import com.aberkane.shopsmart_backend.model.User;
import com.aberkane.shopsmart_backend.model.dto.OrderDto;
import com.aberkane.shopsmart_backend.service.CustomUserDetailService;
import com.aberkane.shopsmart_backend.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    @Autowired
    private final OrderServiceImpl orderService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final CustomUserDetailService customUserDetailService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
        User actualUser = customUserDetailService.getUserInfo(userDetails.getUsername());
        List<Order> orders = orderService.getOrderByUserId(actualUser.getUserId());
        List<OrderDto> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDtos);
    }



    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(Long id) {
        Order order = orderService.getOrderById(id);
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return ResponseEntity.ok(orderDto);
    }

}
