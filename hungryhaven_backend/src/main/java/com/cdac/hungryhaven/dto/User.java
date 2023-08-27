package com.cdac.hungryhaven.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    private Long id;

    @NotBlank(message = "Email must be provided")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must be provided")
    @Size(min = 6, message = "Password must be greater than size 6")
    private String password;

    @NotBlank(message = "Username must be provided")
    private String username;

    private Long restaurantId;
}


