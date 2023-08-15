package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Quantity;

public interface QuantityRepositoryService {
    Quantity updateQuantity(Long itemId, Long restaurantId, int quantity);
    Quantity getQuantity(Long itemId, Long restaurantId) throws Exception;
    
}
