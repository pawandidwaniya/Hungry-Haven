
package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Quantity;
import com.cdac.hungryhaven.repositoryservices.QuantityRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private QuantityRepositoryService quantityRepositoryService;

    @Override
    public Quantity updateQuantity(Long itemId, Long restaurantId, int quantity) {
        Quantity updatedQuantity = null;
        try {
            updatedQuantity = quantityRepositoryService.updateQuantity(itemId, restaurantId, quantity);
        } catch (Exception ex) {
            // Handle the exception appropriately
        }
        return updatedQuantity;
    }

    @Override
    public Quantity getQuantity(Long itemId, Long restaurantId) throws Exception {
        try {
            Quantity quantity = quantityRepositoryService.getQuantity(itemId, restaurantId);
            if (quantity == null) {
                throw new Exception("Quantity not found");
            }
            return quantity;
        } catch (Exception ex) {
            throw ex;
        }

    }
}
