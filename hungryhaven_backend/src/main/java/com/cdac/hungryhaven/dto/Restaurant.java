package com.cdac.hungryhaven.dto;


import lombok.Data;


@Data
public class Restaurant {

    private Long id;
    private String name;
    private String city;
    private String imageUrl;
    private String opensAt;
    private String closesAt;
}
