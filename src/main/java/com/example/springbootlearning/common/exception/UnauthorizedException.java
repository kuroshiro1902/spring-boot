package com.example.springbootlearning.common.exception;

public class UnauthorizedException extends  AppRuntimeException {
  public UnauthorizedException(String message) {
    super(message, org.springframework.http.HttpStatus.UNAUTHORIZED);
  }

  public UnauthorizedException() {
    this("Unauthorized");
  }
}
