package com.example.springbootlearning.common.dto;

public record ResponseData<T>(T data, String message, boolean success) {

  public static <T> ResponseData<T> success(T data) {
    return new ResponseData<>(data, null, true);
  }

  public static <T> ResponseData<T> success(T data, String message) {
    return new ResponseData<>(data, message, true);
  }

  public static <T> ResponseData<T> error(String message) {
    return new ResponseData<>(null, message, false);
  }

  public static <T> ResponseData<T> error(T data, String message) {
    return new ResponseData<>(data, message, false);
  }
}
