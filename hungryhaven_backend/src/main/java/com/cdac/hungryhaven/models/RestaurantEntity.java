package com.cdac.hungryhaven.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

// Java class that maps to MySql table.
@Entity
@Data
@Table(name = "restaurants")
@NoArgsConstructor
public class RestaurantEntity {

    @Id
    private String id;

    @NotBlank
    private String restaurantId;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;

    @NotBlank
    private String opensAt;

    @NotBlank
    private String closesAt;

    @NotBlank
    private List<String> attributes = new ArrayList<>();


}
