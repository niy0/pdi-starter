package com.pidSpringBoot.pidSpringBoot.user;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
