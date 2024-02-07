package com.pricemanager.infrastructure.exception;

import com.pricemanager.infrastructure.persistence.exception.PriceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.BAD_REQUEST)
            .message(errors.toString())
            .build();
    log.error(ErrorMessages.METHOD_ARGUMENT_NOT_VALID, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex) {
    ApiError apiError =
        ApiError.builder()
            .exception(ex.getClass().getSimpleName())
            .status(HttpStatus.NOT_FOUND)
            .message(ex.getMessage())
            .build();
    log.error(ErrorMessages.PRICE_NOT_FOUND, ex.getMessage(), ex);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<Object> handleConversionFailedException(ConversionFailedException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.CONVERSION_FAILED, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.BAD_REQUEST)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.MISSING_SERVLET_REQUEST_PARAMETER, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<Object> handleHandlerMethodValidationException(
      HandlerMethodValidationException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.BAD_REQUEST)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.HANDLER_METHOD_VALIDATION_FAILED, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.BAD_REQUEST)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.METHOD_ARGUMENT_TYPE_MISMATCH, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.BAD_REQUEST)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.HTTP_MESSAGE_NOT_READABLE, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleGenericException(Exception e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.GENERIC_EXCEPTION, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(value = {NullPointerException.class})
  public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
    ApiError apiError =
        ApiError.builder()
            .exception(e.getClass().getSimpleName())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
    log.error(ErrorMessages.NULL_POINTER_EXCEPTION, e.getMessage(), e);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
