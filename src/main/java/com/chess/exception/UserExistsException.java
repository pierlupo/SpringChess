package com.chess.exception;

public class UserExistsException extends Exception{
    public UserExistsException() {
        super("User Exists");
    }
}
