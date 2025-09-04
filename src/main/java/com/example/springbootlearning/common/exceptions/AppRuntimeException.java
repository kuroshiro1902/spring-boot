package com.example.springbootlearning.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AppRuntimeException extends RuntimeException {
  private final HttpStatus status;

  protected AppRuntimeException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
