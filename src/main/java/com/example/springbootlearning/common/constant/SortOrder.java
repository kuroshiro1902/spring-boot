package com.example.springbootlearning.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SortOrder {
  ASC,
  DESC;

  @JsonCreator
  public static SortOrder fromValue(String value) {
    return SortOrder.valueOf(value.toUpperCase());
  }
}