package com.example.springbootlearning.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AppRuntimeException {
  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException() {
    this("Bad request");
  }
}
