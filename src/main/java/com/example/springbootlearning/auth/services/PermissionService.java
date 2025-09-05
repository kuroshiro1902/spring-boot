package com.example.springbootlearning.auth.services;

import com.example.springbootlearning.auth.entities.Permission;
import com.example.springbootlearning.auth.repositories.PermissionRepository;
import com.example.springbootlearning.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {
  private final PermissionRepository permissionRepository;

  public Permission findOneByCodeOrThrow(String code) {
    return permissionRepository.findByCode(code).orElseThrow(()-> new NotFoundException("Permission not found"));
  }

  public Optional<Permission> findOneByCode(String code) {
    return permissionRepository.findByCode(code);
  }

  public Permission createOne(Permission permission) {
    return permissionRepository.save(permission);
  }
}
