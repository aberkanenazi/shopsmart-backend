package com.aberkane.shopsmart_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aberkane.shopsmart_backend.model.Order;
import com.aberkane.shopsmart_backend.model.User;
import com.aberkane.shopsmart_backend.model.dto.OrderDto;
import com.aberkane.shopsmart_backend.model.dto.UserDto;
import com.aberkane.shopsmart_backend.service.CustomUserDetailService;
import com.aberkane.shopsmart_backend.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    private final CustomUserDetailService customUserDetailService;

    private final ModelMapper modelMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User actualUser = customUserDetailService.getUserInfo(userDetails.getUsername());
        User user = userService.getUserProfile(actualUser.getUserId());
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@AuthenticationPrincipal UserDetails userDetails, UserDto userDto) {
        try {
            // Récupérer l'utilisateur actuel
            User actualUser = customUserDetailService.getUserInfo(userDetails.getUsername());

            // Mapper les données du DTO vers l'entité User
            User user = modelMapper.map(userDto, User.class);

            // Mettre à jour le profil utilisateur
            userService.updateUserProfile(actualUser.getUserId(), user);

            // Retourner une réponse avec le statut HTTP 200 et un message de succès ou les
            // données mises à jour
            return ResponseEntity.ok().body("Profile updated successfully");

        } catch (Exception e) {
            // Gestion des erreurs, retourner une réponse avec le statut HTTP 400 ou 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the profile");
        }
    }

    @GetMapping("/user/orders")
    public ResponseEntity<List<OrderDto>> getUserOrders(@AuthenticationPrincipal UserDetails userDetails) {
        User actualUser = customUserDetailService.getUserInfo(userDetails.getUsername());
        System.out.println(actualUser.getUserId() + "************");
        List<Order> userOrders = userService.getUserOrders(actualUser.getUserId());
        System.out.printf("User Orders: %s", userOrders);
        List<OrderDto> orderDtos = userOrders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDtos);

    }
}
