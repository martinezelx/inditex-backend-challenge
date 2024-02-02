package com.pricemanager.infrastructure.exception;

import com.pricemanager.infrastructure.persistence.exception.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST)
                .message(errors.toString())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .exception(ex.getClass().getSimpleName())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        ApiError apiError = ApiError.builder()
                .exception(e.getClass().getSimpleName())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
