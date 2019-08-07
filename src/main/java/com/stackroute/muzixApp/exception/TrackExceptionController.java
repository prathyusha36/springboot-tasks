package com.stackroute.muzixApp.exception;

import com.stackroute.muzixApp.exception.TrackAlreadyExistsException;
import com.stackroute.muzixApp.exception.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> notFoundException(TrackNotFoundException exception) {
        return new ResponseEntity<>("track not found", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> notFoundException(TrackAlreadyExistsException exception) {
        return new ResponseEntity<>("track already exists", HttpStatus.CONFLICT);
       
    }
}
