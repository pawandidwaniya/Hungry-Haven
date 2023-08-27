package com.cdac.hungryhaven.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Cart;
import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Order;
import com.cdac.hungryhaven.exceptions.CartNotFoundException;
import com.cdac.hungryhaven.exceptions.EmptyCartException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;
import com.cdac.hungryhaven.exchanges.CartModifiedResponse;
import com.cdac.hungryhaven.exchanges.GetCartRequest;
import com.cdac.hungryhaven.exchanges.GetOrdersResponse;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.repositories.ItemRepository;
import com.cdac.hungryhaven.repositoryservices.CartRepositoryService;
import com.cdac.hungryhaven.repositoryservices.OrderRepositoryService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CartAndOrderServiceImpl implements CartAndOrderService {
	
	  @Autowired
	  ItemRepository itemRepository;

	  @Autowired
	  CartRepositoryService cartRepositoryService;

	  @Autowired
	  OrderRepositoryService orderRepositoryService;

	  @Autowired
	  MenuService menuService;
	
	
	
	@Override
	public Cart findCartByUserId(GetCartRequest getCartRequest) {
	    Optional<Cart> cart = cartRepositoryService.findCartByUserId(getCartRequest.getUserId());
	    Cart cartPassed = cart.get();
	    return cartPassed;
	}

	@Override
	public Cart findOrCreateCart(Long userId) throws CartNotFoundException {
	    Optional<Cart> cart = cartRepositoryService.findCartByUserId(userId);

	    if (cart.isPresent()) {
	      return cart.get();
	    } else {
	      Cart newCart = new Cart();
	      newCart.setRestaurantId(null);
	      newCart.setUserId(userId);
	      cartRepositoryService.createCart(newCart);
	      cartRepositoryService.findCartByCartId(newCart.getCart_id());
	      return newCart;
	    }
	}

	@Override
	public CartModifiedResponse addItemToCart(Long itemId, Long cartId, Long restaurantId)
			throws ItemNotFromSameRestaurantException, CartNotFoundException {
	    CartModifiedResponse response = new CartModifiedResponse();
	    try {
	      Cart cart = cartRepositoryService.findCartByCartId(cartId);
	      if (cart.getRestaurantId().equals(restaurantId)) {
	    	Item item = menuService.findItem(itemId, restaurantId);
	    	ItemEntity itemEntity = new ItemEntity(item);
	        Cart updatedCart = cartRepositoryService.addItem(itemEntity, cartId, restaurantId);
	        response.setCart(updatedCart);
	        response.setCartResponseType(0);
	      } else {
	        response.setCart(cart);
	        response.setCartResponseType(new ItemNotFromSameRestaurantException().getErrorType());
	      }
	    } catch (CartNotFoundException e) {
	      System.out.println("Cart Not Found: " + e);
	      throw new CartNotFoundException();
	    }
	    return response;
	}

	@Override
	public CartModifiedResponse removeItemFromCart(Long itemId, Long cartId, Long restaurantId) {
		CartModifiedResponse response = new CartModifiedResponse();
	    try {
	        Item item = menuService.findItem(itemId, restaurantId);
	        ItemEntity itemEntity = new ItemEntity(item);
	        Cart updatedCart = cartRepositoryService.removeItem(itemEntity, cartId, restaurantId);
	        response.setCart(updatedCart);
	        response.setCartResponseType(0);
	      } catch (ItemNotFromSameRestaurantException e) {
	        System.out.println("Item Not Found: " + e);
	      } catch (CartNotFoundException e) {
	        System.out.println("Cart Not Found " + e);
	      }
	      return response;
	}

	@Override
	public Order postOrder(Long cartId) throws EmptyCartException {
	    try {
	        Cart cart = cartRepositoryService.findCartByCartId(cartId);
	        if (!cart.getItems().isEmpty()) {
	          Order order = orderRepositoryService.placeOrder(cart);
	          ;
	          return order;
	        } else {
	          System.out.println(cart);
	          throw new EmptyCartException("Cart is Empty");
	        }

	      } catch (CartNotFoundException e) {
	        throw new CartNotFoundException();
	        }
	}

	@Override
	public GetOrdersResponse getAllPlacedOrders(Long restaurantId) {
	    List<Order> orderList = orderRepositoryService.getOrdersByRestaurant(restaurantId);
	    List<Order> finalOrderList = new ArrayList<>();
	    if (orderList.size() > 0) {
	      for (Order order : orderList) {
	        if (order.getStatus().equals("ACCEPTED")
	            || order.getStatus().equals("PREPARING")
	            || order.getStatus().equals("PACKED")) {
	          finalOrderList.add(order);
	        }
	      }
	    }
	    return new GetOrdersResponse(finalOrderList, restaurantId);
	}

	@Override
	public GetOrdersResponse getAllOrders(Long restaurantId) {
	    List<Order> orderList = orderRepositoryService.getOrdersByRestaurant(restaurantId);
	    List<Order> finalOrderList = new ArrayList<>();
	    if (orderList.size() > 0) {
	      for (Order order : orderList) {
	        if (order.getStatus().equals("ACCEPTED")
	            || order.getStatus().equals("PREPARING")
	            || order.getStatus().equals("PACKED")) {
	          finalOrderList.add(order);
	        }
	      }
	    }
	    return new GetOrdersResponse(finalOrderList, restaurantId);
	}

	@Override
	public GetOrdersResponse updateStatusOfPlacedOrders(Long restaurantId, Long orderId, String status) {
	    Order order = orderRepositoryService.getOrderById(orderId);
	    if (order != null ) {
	      Order newOrder = orderRepositoryService.updateStatus(restaurantId, orderId, status);
	    }
	    List<Order> orderList = orderRepositoryService.getOrdersByRestaurant(restaurantId);
	    List<Order> finalOrderList = new ArrayList<>();
	    if (orderList.size() > 0) {
	      for (Order orderInList : orderList) {
	        if (orderInList.getStatus().equals("ACCEPTED")
	            || orderInList.getStatus().equals("PREPARING")
	            || orderInList.getStatus().equals("PACKED")) {
	          finalOrderList.add(orderInList);
	        }
	      }
	    }
	    return new GetOrdersResponse(finalOrderList, restaurantId);
	}

	@Override
	public GetOrdersResponse updateStatusOfOrder(Long restaurantId, Long orderId, String status) {
	    Order order = orderRepositoryService.getOrderById(orderId);
	    if (order != null && order.getStatus().equals("PLACED")) {
	      Order newOrder = orderRepositoryService.updateStatus(restaurantId, orderId, status);
	    }
	    List<Order> orderList = orderRepositoryService.getOrdersByRestaurant(restaurantId);
	    List<Order> finalOrderList = new ArrayList<>();
	    if (orderList.size() > 0) {
	      for (Order orderInList : orderList) {
	        if (orderInList.getStatus().equals("PLACED")) {
	          finalOrderList.add(orderInList);
	        }
	      }
	    }
	    return new GetOrdersResponse(finalOrderList, restaurantId);
	}

}
