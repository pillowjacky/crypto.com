package com.crypto.challenge.exception;

public class ApiError {
    
    private String errorMessage;

    public ApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
