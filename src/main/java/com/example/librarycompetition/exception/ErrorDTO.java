package com.example.librarycompetition.exception;

public record ErrorDTO(
        String errorMessage
) {
    public static ErrorDTO of(String errorMessage) {
        return new ErrorDTO(errorMessage);
    }
}
