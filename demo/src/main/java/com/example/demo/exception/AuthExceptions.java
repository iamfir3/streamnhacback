package com.example.demo.exception;

import lombok.Getter;

@Getter
public class AuthExceptions extends RuntimeException{
    private int status;
    public AuthExceptions(String message, int status){
        super(message);
        this.status=status;
    }

}
