package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<String> applicationErrorHandler(StudentException exception) {

        return ResponseEntity.status(Integer.parseInt(exception.getCode())).
                contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<String> dataAccessExceptionHandler(DataAccessException exception) {
        log.error("DataAccessException occurred while performing DB operations: {}", exception.getMessage(), exception);
        return ResponseEntity.status(500).
                contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
    }
}
