package com.example.springbootlearning.user.repository;

import com.example.springbootlearning.auth.entity.Permission;
import com.example.springbootlearning.user.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository
    extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

  @Query("SELECT p FROM Permission p " +
      "JOIN UserPermission up ON p.id = up.permission.id " +
      "WHERE up.user.id= :userId")
  List<Permission> findPermissionsByUserId(@Param("userId") String userId);
}
