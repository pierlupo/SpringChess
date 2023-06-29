package com.chess.exception;

public class PlayerExistsException extends Exception {
    public PlayerExistsException() {
        super("Player Exists");
    }
}
