package com.chess.exception;

public class GameDoesNotExistException extends Exception{
    public GameDoesNotExistException() {
        super("Game Does Not Exist");
    }
}
