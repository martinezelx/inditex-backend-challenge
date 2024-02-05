package com.pricemanager.infrastructure.exception;

public final class ErrorMessages {

    private ErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRICE_NOT_FOUND = "Price not found for the given date, product ID and brand ID";
}
