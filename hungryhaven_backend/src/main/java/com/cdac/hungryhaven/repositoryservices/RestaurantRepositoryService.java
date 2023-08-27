package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Restaurant;

import java.util.Optional;

public interface RestaurantRepositoryService {
    Restaurant getRestaurantById(Long id);
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
}
