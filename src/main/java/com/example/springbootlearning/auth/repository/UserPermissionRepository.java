package com.example.springbootlearning.auth.repository;

import com.example.springbootlearning.auth.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface UserPermissionRepository extends JpaRepository<UserPermission, String>, JpaSpecificationExecutor<UserPermission> {
  Optional<UserPermission> findByUserIdAndPermissionId(String userId, String permissionId);
}
