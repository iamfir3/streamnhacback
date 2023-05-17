package com.example.demo.exception;


import com.example.demo.response.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value={UserServiceExceptions.class})
    public ResponseEntity<Object> handlerUserServiceException(UserServiceExceptions ex, WebRequest request){
        ErrorMessageResponse errorMessageResponse=new ErrorMessageResponse(ex.getStatus(),ex.getMessage());
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
