package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Quantity;
<<<<<<< HEAD
import com.cdac.hungryhaven.exceptions.ItemNotFoundException;
import com.cdac.hungryhaven.exceptions.RestaurantNotFoundException;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.models.RestaurantEntity;
import com.cdac.hungryhaven.repositories.ItemRepository;
import com.cdac.hungryhaven.repositories.RestaurantRepository;
=======
import com.cdac.hungryhaven.repositoryservices.QuantityRepositoryService;
>>>>>>> 83f7363b6edad8860f89f25e2d885da851b08bab

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private QuantityRepositoryService quantityRepositoryService;

    @Override
<<<<<<< HEAD
    public Quantity updateQuantity(Long itemId, Long restaurantId, int quantityValue) {
        ItemEntity item = retrieveItemById(itemId);
        RestaurantEntity restaurant = retrieveRestaurantById(restaurantId);

        // Update quantity logic here

        Quantity updatedQuantity = new Quantity();
        updatedQuantity.setItemId(item);
        updatedQuantity.setRestaurantId(restaurant);
        updatedQuantity.setQuantity(quantityValue);

=======
    public Quantity updateQuantity(Long itemId, Long restaurantId, int quantity) {
        Quantity updatedQuantity = null;
        try {
            updatedQuantity = quantityRepositoryService.updateQuantity(itemId, restaurantId, quantity);
        } catch (Exception ex) {
            // Handle the exception appropriately
        }
>>>>>>> 83f7363b6edad8860f89f25e2d885da851b08bab
        return updatedQuantity;
    }

    @Override
    public Quantity getQuantity(Long itemId, Long restaurantId) throws Exception {
        try {
            Quantity quantity = quantityRepositoryService.getQuantity(itemId, restaurantId);
            if (quantity == null) {
                throw new Exception("Quantity not found");
            }
            return quantity;
        } catch (Exception ex) {
            throw ex;
        }
        
    }
}
