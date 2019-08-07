package com.stackroute.muzixApp.exception;

public class TrackAlreadyExistsException extends Exception {
    private String message;
    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message=message;
    }
    public TrackAlreadyExistsException() {

    }

}
