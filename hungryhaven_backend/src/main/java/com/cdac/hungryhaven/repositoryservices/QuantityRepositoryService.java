package com.cdac.hungryhaven.repositoryservices;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.ItemQuantity;

@Service
public interface QuantityRepositoryService {
	  public ItemQuantity updateQuantity(Long itemId,  Long restaurantId, int quantity) throws Exception;

	  public ItemQuantity getQuantity(Long itemId, Long restaurantId) throws Exception;
    
}
