package com.cdac.hungryhaven.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Table(name = "quantities")
@Data
public class QuantityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Long itemId;

    @NotBlank
    private long restaurantId;

    @NotBlank
    private int quantity;
}
