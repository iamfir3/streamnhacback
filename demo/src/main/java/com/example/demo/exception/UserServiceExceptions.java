package com.example.demo.exception;

import lombok.Getter;

@Getter
public class UserServiceExceptions extends RuntimeException{
    private int status;
    public UserServiceExceptions(String message, int status){
        super(message);
        this.status=status;
    }
}
