package com.example.springbootlearning.user.services;

import com.example.springbootlearning.auth.entities.Permission;
import com.example.springbootlearning.user.entities.User;
import com.example.springbootlearning.user.entities.UserPermission;
import com.example.springbootlearning.user.repositories.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPermissionService {

  private final UserPermissionRepository userPermissionRepository;

  public UserPermission assignPermission(User user, Permission permission) {
    return assignPermission(user, permission, Optional.empty());
  }

  public UserPermission assignPermission(User user, Permission permission, Optional<User> assignedBy) {
    UserPermission userPermission = new UserPermission();
    userPermission.setUser(user);
    userPermission.setPermission(permission);
    assignedBy.ifPresent(userPermission::setAssignedBy);

    return userPermissionRepository.save(userPermission);
  }

  @Transactional
  public void revokePermission(String userId, String permissionId) {
    userPermissionRepository.findByUserIdAndPermissionId(userId, permissionId)
        .ifPresent(userPermissionRepository::delete);
  }
}