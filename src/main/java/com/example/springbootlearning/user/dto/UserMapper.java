package com.example.springbootlearning.user.dto;

import com.example.springbootlearning.user.User;
import com.example.springbootlearning.user.dto.requests.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User toEntity(CreateUserRequest request) {
    User user = new User();
    user.setName(request.name());
    user.setEmail(request.email());
    return user;
  }

//  void updateEntity(@MappingTarget User user, UserUpdateRequest request);

//  UserResponse toResponse(User user);

//  UserAdminResponse toAdminResponse(User user);

//  UserSummaryResponse toSummaryResponse(User user);
}
