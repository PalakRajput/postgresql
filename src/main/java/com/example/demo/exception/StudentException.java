package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentException extends RuntimeException {
    private final String message;
    private final String code;

    public StudentException(String message, String code){
        super(message);
        this.message = message;
        this.code = code;
    }
}
