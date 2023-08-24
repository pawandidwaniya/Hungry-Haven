package com.cdac.hungryhaven.exceptions;

public class ItemNotFromSameRestaurantException extends HungryHavenException {
    
    public ItemNotFromSameRestaurantException(String message) {
        super(message);
    }

	@Override
	public int getErrorType() {
		// TODO Auto-generated method stub
		return ITEM_NOT_FROM_SAME_RESTAURANT;
	}
    
    // You can add additional constructors or methods if needed
}
