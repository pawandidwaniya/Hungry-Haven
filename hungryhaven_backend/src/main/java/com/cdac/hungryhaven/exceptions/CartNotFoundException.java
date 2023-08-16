package com.cdac.hungryhaven.exceptions;

public class CartNotFoundException extends HungryHavenException {

	@Override
	  public int getErrorType() {
	    return CART_NOT_FOUND;
	  }
}
