package com.chess.exception;

public class PlayerNotActiveException extends Exception{
    public PlayerNotActiveException() {
        super("Player Not Active");
    }
}
