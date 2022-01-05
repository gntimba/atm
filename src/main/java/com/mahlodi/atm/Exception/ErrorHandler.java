package com.mahlodi.atm.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(NotFoundException nfe) {
        log.error(nfe.getMessage());
        return new ResponseEntity<ErrorType>(
                new ErrorType(
                        new Date(System.currentTimeMillis()).toString(),
                        "404- NOT FOUND",
                        nfe.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendanceExist.class)
    public ResponseEntity<ErrorType> handleBadRequest(AttendanceExist bre) {
        log.error(bre.getMessage());
        return new ResponseEntity<ErrorType>(
                new ErrorType(
                        new Date(System.currentTimeMillis()).toString(),
                        "409 - Duplicate",
                        bre.getMessage()),
                HttpStatus.CONFLICT);
    }
}
