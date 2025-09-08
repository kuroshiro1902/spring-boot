package com.example.springbootlearning.auth.dto;

import com.example.springbootlearning.auth.dto.request.SignupRequest;
import com.example.springbootlearning.auth.dto.response.LoginResponse;
import com.example.springbootlearning.auth.dto.response.SignupResponse;
import com.example.springbootlearning.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
  public User toUserEntity(SignupRequest request) {
    User user = new User();
    user.setName(request.name());
    user.setEmail(request.email());
    user.setPassword(request.password());
    user.setUsername(request.email()); // Username is email by default
    return user;
  }

  public SignupResponse toSignupResponse(User user) {
    return new SignupResponse(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getUsername(),
        user.getCreatedAt()
    );
  }

  public LoginResponse toLoginResponse(User user, String accessToken) {
    return new LoginResponse(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getUsername(),
        user.getCreatedAt(),
        user.getUpdatedAt(),
        accessToken
    );
  }
}
