package com.pricemanager.infrastructure.exception;

public final class ErrorMessages {

  private ErrorMessages() {
    throw new IllegalStateException("Utility class");
  }

  public static final String PRICE_NOT_FOUND =
      "Price not found for the given date, product ID and brand ID";
  public static final String METHOD_ARGUMENT_NOT_VALID = "Method argument not valid: {}";
  public static final String CONVERSION_FAILED = "Conversion failed: {}";
  public static final String MISSING_SERVLET_REQUEST_PARAMETER =
      "Missing servlet request parameter: {}";
  public static final String HANDLER_METHOD_VALIDATION_FAILED =
      "Handler method validation failed: {}";
  public static final String METHOD_ARGUMENT_TYPE_MISMATCH = "Method argument type mismatch: {}";
  public static final String HTTP_MESSAGE_NOT_READABLE = "HTTP message not readable: {}";
  public static final String GENERIC_EXCEPTION = "Generic exception: {}";
  public static final String NULL_POINTER_EXCEPTION = "Null pointer exception: {}";
}
