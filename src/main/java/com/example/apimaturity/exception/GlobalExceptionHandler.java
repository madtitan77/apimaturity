package com.example.apimaturity.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.apimaturity.controller.UserController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> handleClientNotFoundException(ClientNotFoundException ex) {
        // TODO? create a custom error response class
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Log the exception details for debugging purposes
        logger.error("Error: " + ex.getMessage());
        // TODO? create a custom error response class
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "Email already exists.");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    // You can define ErrorResponse class or use any other way to send error details
    static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters and Setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}