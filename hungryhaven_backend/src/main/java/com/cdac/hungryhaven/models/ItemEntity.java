package com.cdac.hungryhaven.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "items")
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String itemId;

    @NotBlank
    private String name;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private Double price;

    @NotBlank
    private List<String> attributes = new ArrayList<>();
}
