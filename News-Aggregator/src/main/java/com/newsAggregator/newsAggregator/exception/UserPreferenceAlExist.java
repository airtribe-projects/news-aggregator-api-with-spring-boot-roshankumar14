package com.newsAggregator.newsAggregator.exception;

public class UserPreferenceAlExist extends Exception {

    public UserPreferenceAlExist(String userProvidedPreferenceAlreadyExist) {
        super(userProvidedPreferenceAlreadyExist);
    }
}
