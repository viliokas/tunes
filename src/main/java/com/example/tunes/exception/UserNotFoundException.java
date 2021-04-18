package com.example.tunes.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super("UserId " + userId + " not found");
    }
}

