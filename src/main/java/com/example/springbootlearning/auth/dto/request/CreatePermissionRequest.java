package com.example.springbootlearning.auth.dto.request;

import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePermissionRequest (
  @NotBlank
  @Size(min = 1, max = 255)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String name,

  @NotBlank
  @Size(min = 1, max = 255)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String code,

  @Size(max = 1000)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String description) {
}
