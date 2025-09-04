package com.example.springbootlearning.auth.dto.responses;

import java.time.LocalDateTime;

public record LoginResponse(
  String id,
  String name,
  String email,
  String username,
  LocalDateTime createdAt,
  LocalDateTime updatedAt,
  String accessToken
) {
}
