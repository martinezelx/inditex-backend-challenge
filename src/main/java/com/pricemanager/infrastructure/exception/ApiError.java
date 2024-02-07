package com.pricemanager.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ApiError {

  private String exception;
  private HttpStatus status;
  private String message;
}
