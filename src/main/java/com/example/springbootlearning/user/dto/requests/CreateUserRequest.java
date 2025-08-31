package com.example.springbootlearning.user.dto.requests;

import com.example.springbootlearning.base.deserializers.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;

public record CreateUserRequest(
  @NotBlank(message = "Name must not be blank")
  @Size(min = 5, max = 50, message = "Name length must be between 5 and 50")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String name,

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Email must be a valid email address")
  @Size(max = 100, message = "Email length must be at most 100")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String email) {
}