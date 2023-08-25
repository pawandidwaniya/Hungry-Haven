package com.cdac.hungryhaven.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;


    @Column(name = "email", unique = true)
    @NotBlank(message = "Email must be provided")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password must be provided")
    @Size(min = 6, message = "Password must be greater than size 6")
    private String password;


    @Column(name = "username")
    @NotBlank(message = "Username must be provided")
    private String username;

    @Column(name = "restaurantId")
    private Long restaurantId;



}

