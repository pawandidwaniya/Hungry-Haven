package com.cdac.hungryhaven.exceptions;

abstract class HungryHavenException extends RuntimeException {
	
	static final int CART_NOT_FOUND = 103;
	
	HungryHavenException(){
		
	}
	
	HungryHavenException(String msg){
		super(msg);
	}
	
	public abstract int getErrorType();

}
