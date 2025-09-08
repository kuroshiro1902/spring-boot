package com.example.springbootlearning.auth.service;

import com.example.springbootlearning.auth.entity.Permission;
import com.example.springbootlearning.auth.repository.PermissionRepository;
import com.example.springbootlearning.common.exception.NotFoundException;
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
