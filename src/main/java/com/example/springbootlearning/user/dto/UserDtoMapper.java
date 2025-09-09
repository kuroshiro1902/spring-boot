package com.example.springbootlearning.user.dto;

import com.example.springbootlearning.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public final class UserDtoMapper {

  public UserWithoutPassword toUserWithoutPassword(User user) {
    return new UserWithoutPassword(
      user.getId(),
      user.getCreatedAt(),
      user.getUpdatedAt(),
      user.getDeletedAt(),
      user.getUsername(),
      user.getName(),
      user.getEmail()
    );
  }

//  void updateEntity(@MappingTarget User user, UserUpdateRequest request);

//  UserResponse toResponse(User user);

//  UserAdminResponse toAdminResponse(User user);

//  UserSummaryResponse toSummaryResponse(User user);
}
