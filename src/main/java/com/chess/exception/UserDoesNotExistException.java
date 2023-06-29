package com.chess.exception;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException() {
        super("User Does Not Exist");
    }
}
