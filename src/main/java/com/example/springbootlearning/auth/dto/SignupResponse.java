package com.example.springbootlearning.auth.dto;

import java.time.LocalDateTime;

public record SignupResponse(
    String id,
    String name,
    String email,
    String username,
    LocalDateTime createdAt
) {
}
