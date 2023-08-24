package com.cdac.hungryhaven.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

//    @NotBlank
//    private long restaurantId;

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
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;


}
