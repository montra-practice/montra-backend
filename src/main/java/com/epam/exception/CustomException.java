package com.epam.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private int code;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }
}
