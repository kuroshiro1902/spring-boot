package com.example.springbootlearning.auth.service;

import com.example.springbootlearning.auth.entity.Permission;
import com.example.springbootlearning.auth.repository.UserPermissionRepository;
import com.example.springbootlearning.user.entity.User;
import com.example.springbootlearning.auth.entity.UserPermission;
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