package com.newsAggregator.newsAggregator.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String ExceptionMsg) {
        super(ExceptionMsg);
    }
}
