package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Quantity;
import com.cdac.hungryhaven.exceptions.ItemNotFoundException;
import com.cdac.hungryhaven.exceptions.RestaurantNotFoundException;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.models.RestaurantEntity;
import com.cdac.hungryhaven.repositories.ItemRepository;
import com.cdac.hungryhaven.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private ItemRepository itemRepository; // Inject your ItemRepository here

    @Autowired
    private RestaurantRepository restaurantRepository; // Inject your RestaurantRepository here

    @Override
    public Quantity updateQuantity(Long itemId, Long restaurantId, int quantityValue) {
        ItemEntity item = retrieveItemById(itemId);
        RestaurantEntity restaurant = retrieveRestaurantById(restaurantId);

        // Update quantity logic here

        Quantity updatedQuantity = new Quantity();
        updatedQuantity.setItem(item);
        updatedQuantity.setRestaurant(restaurant);
        updatedQuantity.setQuantity(quantityValue);

        return updatedQuantity;
    }

    @Override
    public Quantity getQuantity(Long itemId, Long restaurantId) throws Exception {
        ItemEntity item = retrieveItemById(itemId);
        RestaurantEntity restaurant = retrieveRestaurantById(restaurantId);

        // Retrieve quantity logic here

        Quantity quantity = new Quantity();
        quantity.setItem(item);
        quantity.setRestaurant(restaurant);
        // Set retrieved quantity value

        return quantity;
    }

    private ItemEntity retrieveItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + itemId));
    }

    private RestaurantEntity retrieveRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId));
    }
}

