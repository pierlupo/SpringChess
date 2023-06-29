package com.chess.exception;

public class EmptyFieldsException extends Exception{

    public EmptyFieldsException(String message){
        super(message);
    }


    public static EmptyFieldsException with(String... fields){
        String message = "Need Field filled up : ";
        for(String f : fields) {
            message += f + " ";
        }
        return new EmptyFieldsException(message);
    }
}
