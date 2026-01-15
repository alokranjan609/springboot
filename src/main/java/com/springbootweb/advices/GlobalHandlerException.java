package com.springbootweb.advices;

import com.springbootweb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        ApiError apiError=ApiError.builder().
                status(HttpStatus.NOT_FOUND).
                message(resourceNotFoundException.getMessage()).build();
        return buildErrorResponseEntity(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerhandleError(Exception exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);

    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());


        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .suberrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        String message = "Invalid value for parameter: "
                + ex.getName();

ApiError apiError=ApiError.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(message)
        .build();
return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
