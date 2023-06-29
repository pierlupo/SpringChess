package com.chess.exception;

public class UserNotActiveException extends Exception{
    public UserNotActiveException() {
        super("User Not Active");
    }
}
