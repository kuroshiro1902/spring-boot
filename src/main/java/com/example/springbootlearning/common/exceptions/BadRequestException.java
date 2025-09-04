package com.example.springbootlearning.common.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AppRuntimeException {
  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException() {
    this("Bad request");
  }
}
