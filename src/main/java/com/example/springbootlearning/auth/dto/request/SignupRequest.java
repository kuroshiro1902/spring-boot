package com.example.springbootlearning.auth.dto.request;

import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
  @NotBlank(message = "Name must not be blank")
  @Size(min = 5, max = 50, message = "Name length must be between 5 and 50")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String name,

  @NotBlank(message = "Password must not be blank")
  @Size(min = 8, max = 100, message = "Password length must be between 8 and 100")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String password,

  @NotBlank(message = "Email must not be blank")
  @Email(
    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
    message = "Email must be a valid email address"
  )
  @Size(max = 100, message = "Email length must be at most 100")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String email) {
}