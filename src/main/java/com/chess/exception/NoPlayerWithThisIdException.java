package com.chess.exception;

public class NoPlayerWithThisIdException extends Exception{
    public NoPlayerWithThisIdException() {
        super("No Player with this id");
    }
}
