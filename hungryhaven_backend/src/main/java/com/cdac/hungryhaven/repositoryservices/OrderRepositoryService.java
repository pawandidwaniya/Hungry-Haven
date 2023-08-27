package com.cdac.hungryhaven.repositoryservices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Cart;
import com.cdac.hungryhaven.dto.Order;

@Service
public interface OrderRepositoryService {

	  /**
	   * TODO: CRIO_TASK_MODULE_MENUAPI - Implement placeOrder.
	   * Place order based on the cart.
	   * @param cart - cart to use for placing order.
	   * @return return - the order that was just placed.
	   */
	  Order placeOrder(Cart cart);

	  List<Order> getOrdersByRestaurant(Long restaurantId);

	  Order updateStatus(Long restaurantId, Long orderId, String status);

	  Order getOrderById(Long orderId);
}
