package com.cdac.hungryhaven.exceptions;

public class EmptyCartException extends HungryHavenException {
	    public EmptyCartException(String message) {
		    super(message);
		  }

		  @Override
		  public int getErrorType() {
		    return EMPTY_CART;
		  }
}
