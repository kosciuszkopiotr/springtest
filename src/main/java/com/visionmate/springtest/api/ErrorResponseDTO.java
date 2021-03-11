package com.visionmate.springtest.api;

public class ErrorResponseDTO {

    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponseDTO() {}

    public ErrorResponseDTO withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorResponseDTO withMessage(String message) {
        this.message = message;
        return this;
    }

}
