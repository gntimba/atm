package com.mahlodi.atm.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(NotFoundException nfe){

        return new ResponseEntity<ErrorType>(
                new ErrorType(
                        new Date(System.currentTimeMillis()).toString(),
                        "404- NOT FOUND",
                        nfe.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
