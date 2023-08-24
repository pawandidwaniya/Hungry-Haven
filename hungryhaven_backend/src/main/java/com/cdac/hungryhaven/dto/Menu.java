package com.cdac.hungryhaven.dto;

import com.cdac.hungryhaven.models.RestaurantEntity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {

    private Long id;
    private RestaurantEntity restaurant;
    private List<Item> items = new ArrayList();
    
}
