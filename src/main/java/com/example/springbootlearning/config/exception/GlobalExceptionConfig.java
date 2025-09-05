package com.example.springbootlearning.config.exception;

import com.example.springbootlearning.common.exceptions.AppRuntimeException;
import com.example.springbootlearning.common.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionConfig {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    String message = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
    errors.put("message", message);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Request body is missing or invalid JSON");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Duplicate key or data integrity violation");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(AppRuntimeException.class)
  public ResponseEntity<Map<String, String>> handleAppRuntimeException(AppRuntimeException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(errors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Internal server error occurred");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
  }

}
