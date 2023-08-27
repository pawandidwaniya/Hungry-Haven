package com.cdac.hungryhaven.services;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.ItemQuantity;

@Service
public interface QuantityService {

	  public ItemQuantity updateQuantity (Long itemId, Long restaurantId, int quantity);

	  public ItemQuantity getQuantity(Long itemId, Long restaurantId) throws Exception;
}
