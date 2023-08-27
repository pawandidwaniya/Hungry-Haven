package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Menu;
import com.cdac.hungryhaven.exceptions.ItemNotFoundInRestaurantMenuException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;
import com.cdac.hungryhaven.exchanges.GetMenuResponse;

public interface MenuService {
	/**
	   * Return the restaurant menu.
	   * @param restaurantId id of the restaurant
	   * @return the restaurant's menu
	   */
	  GetMenuResponse findMenu(Long restaurantId);

	  /**
	   * Find the item in the restaurant using restaurantId/itemId and return the item if found.
	   * @param itemId id of the item
	   * @param restaurantId id of the restaurant
	   * @return item if found
	   * @exception ItemNotFoundInRestaurantMenuException if the item is not found
	   */
	  Item findItem(Long itemId, Long restaurantId) throws ItemNotFoundInRestaurantMenuException,
	      ItemNotFromSameRestaurantException;

	  GetMenuResponse addItem(Item item, Long restaurantId) throws Exception;

	  GetMenuResponse removeItem(Long itemId, Long restaurantId) throws Exception;

	  GetMenuResponse updateItem(Item item, Long restaurantId) throws ItemNotFoundInRestaurantMenuException;;
}
