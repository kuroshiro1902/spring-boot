package com.example.springbootlearning.user.dto;

import java.time.LocalDateTime;

public record UserWithoutPassword(
  String id,
  LocalDateTime createdAt,
  LocalDateTime updatedAt,
  LocalDateTime deletedAt,
  String username,
  String name,
  String email
){}
