package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Quantity;

public interface QuantityService {

    /**
     * Updates the quantity for a specific item and restaurant.
     *
     * @param itemId       The ID of the item.
     * @param restaurantId The ID of the restaurant.
     * @param quantity     The new quantity value.
     * @return The updated quantity.
     */
    Quantity updateQuantity(Long itemId, Long restaurantId, int quantity);

    /**
     * Retrieves the quantity for a specific item and restaurant.
     *
     * @param itemId       The ID of the item.
     * @param restaurantId The ID of the restaurant.
     * @return The quantity information.
     * @throws Exception If the quantity information cannot be retrieved.
     */
    Quantity getQuantity(Long itemId, Long restaurantId) throws Exception;
}
