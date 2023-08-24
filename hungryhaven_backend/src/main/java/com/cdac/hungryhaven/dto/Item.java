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

    @NotBlank(message = "Name must be provided")
    private String name;

    @NotBlank(message = "Image must be provided")
    private String imageUrl;

    @NotBlank(message = "Price must be provided")
    private Double price;

}
