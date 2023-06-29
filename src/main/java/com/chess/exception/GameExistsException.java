package com.chess.exception;

public class GameExistsException extends Exception{
    public GameExistsException() {
        super("Game Exists");
    }
}
