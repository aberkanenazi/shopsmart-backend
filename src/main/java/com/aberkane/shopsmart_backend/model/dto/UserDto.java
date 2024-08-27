package com.aberkane.shopsmart_backend.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phoneNumber;

    @Size(max = 500, message = "Address must be at most 500 characters")
    private String address;

    @Size(max = 100, message = "City must be at most 100 characters")
    private String city;

    @Size(max = 20, message = "Postal code must be at most 20 characters")
    private String postalCode;

    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @NotNull(message = "Creation date is mandatory")
    private LocalDateTime createdAt;

    private Boolean isActive;

    @NotBlank(message = "Role is mandatory")
    @Size(max = 50, message = "Role must be at most 50 characters")
    private String role;

    // Constructeur sans arguments
    public UserDto() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    // Autre constructeur pour initialiser certains champs
    public UserDto(String firstName, String lastName, String email, String phoneNumber, String address, String city, String postalCode, String country, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }
}
