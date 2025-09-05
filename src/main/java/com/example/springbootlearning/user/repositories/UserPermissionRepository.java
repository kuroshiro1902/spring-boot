package com.example.springbootlearning.user.repositories;

import com.example.springbootlearning.user.entities.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, String>, JpaSpecificationExecutor<UserPermission> {
  Optional<UserPermission> findByUserIdAndPermissionId(String userId, String permissionId);
}
