package com.cdac.hungryhaven.exceptions;

public class ItemNotFoundInRestaurantMenuException extends HungryHavenException {
    
    public ItemNotFoundInRestaurantMenuException(String message) {
        super(message);
    }

    @Override
    public int getErrorType() {
        return ITEM_NOT_FOUND_IN_RESTAURANT_MENU; // Define an appropriate error code for item not found in restaurant menu
    }
    
    // You can add additional constructors or methods if needed
}
