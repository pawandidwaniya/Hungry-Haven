package com.cdac.hungryhaven.services;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Cart;
import com.cdac.hungryhaven.dto.Order;
import com.cdac.hungryhaven.exceptions.CartNotFoundException;
import com.cdac.hungryhaven.exceptions.EmptyCartException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;
import com.cdac.hungryhaven.exchanges.CartModifiedResponse;
import com.cdac.hungryhaven.exchanges.GetCartRequest;
import com.cdac.hungryhaven.exchanges.GetOrdersResponse;

@Service
public interface CartAndOrderService {
	  Cart findCartByUserId(GetCartRequest getCartRequest);

	  Cart findOrCreateCart(Long userId) throws CartNotFoundException;

	  /**
	   * TODO: CRIO_TASK_MODULE_MENUAPI - Implement addItemToCart.
	   * Add item to the given cart.
	   *  - All items added should be from same restaurant
	   *  - If the above constraint is not satisfied, throw ItemNotFromSameRestaurantException exception
	   * @param itemId - id of the item to be added
	   * @param cartId - id of the cart where item should be added
	   * @param restaurantId - id of the restaurant where the given item comes from
	   * @return - return - CartModifiedResponse
	   * @throws ItemNotFromSameRestaurantException - when item to be added comes from 
	   *     different restaurant. You should set cartResponseType to 102(ITEM_NOT_FROM_SAME_RESTAURANT)
	   */
	  CartModifiedResponse addItemToCart(Long itemId, Long cartId, Long restaurantId)
	      throws ItemNotFromSameRestaurantException, CartNotFoundException;

	  /**
	   * TODO: CRIO_TASK_MODULE_MENUAPI - Implement removeItemFromCart.
	   * Remove item from the given cart.
	   * @param itemId - id of the item to be removed
	   * @param cartId - id of the cart where item should be removed
	   * @param restaurantId - id of the restaurant where the given item comes from
	   * @return - return - CartModifiedResponse, set cartResponseType to 0
	   */
	  CartModifiedResponse removeItemFromCart(Long itemId, Long cartId, Long restaurantId);

	  /**
	   * TODO: CRIO_TASK_MODULE_MENUAPI - Implement postOrder.
	   * Place order for the given cart
	   * @param cartId - id of the cart to be converted to order
	   * @return Order - return the order that was just placed
	   * @throws EmptyCartException - should throw this exception when cart is empty
	   */
	  Order postOrder(Long cartId) throws EmptyCartException;

	  GetOrdersResponse getAllPlacedOrders(Long restaurantId);

	  GetOrdersResponse getAllOrders(Long restaurantId);

	  GetOrdersResponse updateStatusOfPlacedOrders(Long restaurantId, Long orderId, String status);

	  GetOrdersResponse updateStatusOfOrder(Long restaurantId, Long orderId, String status);

}
