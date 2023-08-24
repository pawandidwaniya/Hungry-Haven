package com.cdac.hungryhaven.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Data
@Table(name = "items")
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "item_name")
    @NotBlank
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "item_price")
    @NotBlank
    private Double price;
}
