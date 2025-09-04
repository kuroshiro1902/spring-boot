package com.example.springbootlearning.common.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppRuntimeException {
  public NotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public NotFoundException() {
    this("Resource not found");
  }
}
