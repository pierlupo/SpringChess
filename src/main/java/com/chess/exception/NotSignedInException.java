package com.chess.exception;

public class NotSignedInException extends Exception{

    public NotSignedInException(){
        super("You Have Not Signed In");
    }
}
