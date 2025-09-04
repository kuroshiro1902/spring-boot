package com.example.springbootlearning.auth.dto;

import com.example.springbootlearning.auth.dto.requests.SignupRequest;
import com.example.springbootlearning.auth.dto.responses.LoginResponse;
import com.example.springbootlearning.auth.dto.responses.SignupResponse;
import com.example.springbootlearning.user.User;
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

  public LoginResponse toLoginResponse(User user) {
    return new LoginResponse(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getUsername(),
        user.getCreatedAt(),
        user.getUpdatedAt()
    );
  }
}
