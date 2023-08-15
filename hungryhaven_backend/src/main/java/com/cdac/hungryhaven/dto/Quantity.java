package com.cdac.hungryhaven.dto;

import lombok.Data;

@Data
public class Quantity {
    private Long id;
    private Long itemId;
    private Long restaurantId;
    private int quantity;
}
