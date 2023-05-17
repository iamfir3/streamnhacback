package com.example.demo.exception;

public enum ErrorMessage {
    MISSING_REQUIED_FIELD("Missing requied fied",1),
    INTERVAL_SERVER_ERROR("Interval server error",-1),
    EMAIL_ALREADY_EXIST("Email already exist",1),
    AUTHENTICATION_FAIL("Authentication fail",1),
    NOT_VALID_EMAIL("Email is not found",1),
    USER_NOT_OWNER("user is invalid",-1),
    TOKEN_IS_NULL("Token is null",-1),
    TOKEN_INVALID("Token invalid",-1);

    private String errorMessage;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    ErrorMessage(String errorMessage, int status){
        this.errorMessage=errorMessage;
        this.status=status;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage=errorMessage;
    }
}
