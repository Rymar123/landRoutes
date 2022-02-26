package com.example.landroutes.exception;

public class EmptyTreeLevelException extends RuntimeException {
    public EmptyTreeLevelException(int level) {
        super("No nodes at level " + level);
    }
}