package com.example.springbootlearning.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice // Có thể dùng @RestControllerAdvice thay cho @ControllerAdvice + @ResponseBody
public class GlobalExceptionConfig {

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    String message = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
    errors.put("message", message);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Request body is missing or invalid JSON");
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    String rootMessage = Objects.requireNonNull(ex.getRootCause()).getMessage();

    String detailMessage = rootMessage;
    int detailIndex = rootMessage.indexOf("Detail:");
    if (detailIndex != -1) {
      detailMessage = rootMessage.substring(detailIndex + 7).trim();
    }

    errors.put("message", detailMessage);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
