package com.cdac.hungryhaven.exceptions;



public class UserNotFoundException extends HungryHavenException {
    public UserNotFoundException(){

    }

    @Override
    public int getErrorType() {
        return USER_NOT_FOUND;
    }
}
