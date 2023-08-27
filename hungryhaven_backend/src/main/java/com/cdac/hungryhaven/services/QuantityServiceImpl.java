
package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.ItemQuantity;
import com.cdac.hungryhaven.repositoryservices.QuantityRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {
	
	@Autowired
	private QuantityRepositoryService quantityRepositoryService;

	@Override
	public ItemQuantity updateQuantity(Long itemId, Long restaurantId, int quantity) {
	    ItemQuantity itemQuantity = null;
	    try {
	    	itemQuantity = quantityRepositoryService.updateQuantity(itemId, restaurantId, quantity);
	    } catch (Exception ex) {

	    }

	    return itemQuantity;
	}

	@Override
	public ItemQuantity getQuantity(Long itemId, Long restaurantId) throws Exception {
	    ItemQuantity itemQuantity = null;

	    try {
	      itemQuantity = quantityRepositoryService.getQuantity(itemId, restaurantId);
	    } catch (Exception ex) {

	      throw  ex;
	    }

	    return itemQuantity;
	}

	
	
	 

    
}
