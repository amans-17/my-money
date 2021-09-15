package com.mymoney.exceptions;

public class InvalidCommandException extends Exception{
    @Override 
    public String toString() {
        return "Invalid Command";
    }
}
