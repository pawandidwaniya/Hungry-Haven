package com.cdac.hungryhaven.exceptions;

abstract class HungryHavenException extends RuntimeException {
	
	static final int CART_NOT_FOUND = 103;
	static final int ITEM_NOT_FOUND_IN_RESTAURANT_MENU = 105;
	static final int ITEM_NOT_FROM_SAME_RESTAURANT = 106;

	static final int USER_NOT_FOUND = 104;
	HungryHavenException(){
		
	}
	
	HungryHavenException(String msg){
		super(msg);
	}
	
	public abstract int getErrorType();

}
