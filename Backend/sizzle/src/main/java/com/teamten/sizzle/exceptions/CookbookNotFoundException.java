package com.teamten.sizzle.exceptions;

public class CookbookNotFoundException extends RuntimeException {
    public CookbookNotFoundException(String message) {
        super(message);
    }
}
