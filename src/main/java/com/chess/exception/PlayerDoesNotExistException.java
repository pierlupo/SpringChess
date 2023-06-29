package com.chess.exception;

public class PlayerDoesNotExistException extends Exception{
    public PlayerDoesNotExistException() {
        super("Player Does Not Exist");
    }
}
