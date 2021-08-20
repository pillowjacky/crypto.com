package com.crypto.challenge.exception;

public class ExchangeToSameCurrencyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExchangeToSameCurrencyException(String message) {
        super(message);
    }
}
