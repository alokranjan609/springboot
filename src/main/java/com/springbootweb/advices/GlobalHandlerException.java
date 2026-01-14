package com.springbootweb.advices;

import com.springbootweb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        ApiError apiError=ApiError.builder().
                status(HttpStatus.NOT_FOUND).
                message(resourceNotFoundException.getMessage()).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(),
                                error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        String message = "Invalid value for parameter: "
                + ex.getName();

        return ResponseEntity
                .badRequest()
                .body(message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

}
