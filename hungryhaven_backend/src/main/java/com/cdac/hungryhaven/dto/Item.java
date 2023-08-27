package com.cdac.hungryhaven.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Long itemId;


    private String name;


    private String imageUrl;

    private Double price;

}
