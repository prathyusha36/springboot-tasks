package com.stackroute.muzixApp.controller;

import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> notFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("track not found", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> notFoundException(UserAlreadyExistsException exception) {
        return new ResponseEntity<>("track already exists", HttpStatus.CONFLICT);
    }
}
