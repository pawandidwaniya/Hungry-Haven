package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Menu;
import com.cdac.hungryhaven.exceptions.ItemNotFoundInRestaurantMenuException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;

public interface MenuService {
	 Menu findMenu(Long restaurantId);

	  /**
	   * Find the item in the restaurant using restaurantId/itemId and return the item if found.
	   * @param itemId id of the item
	   * @param restaurantId id of the restaurant
	   * @return item if found
	   * @exception ItemNotFoundInRestaurantMenuException if the item is not found
	   */
	  Item findItem(Long itemId, Long restaurantId) throws ItemNotFoundInRestaurantMenuException,
	      ItemNotFromSameRestaurantException;

	  Menu addItem(Item item, Long restaurantId) throws Exception;

	  Menu removeItem(Long itemId, Long restaurantId) throws Exception;

	  Menu updateItem(Item item, Long restaurantId) throws ItemNotFoundInRestaurantMenuException;
}
