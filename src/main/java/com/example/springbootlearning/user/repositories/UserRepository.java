package com.example.springbootlearning.user.repositories;

import com.example.springbootlearning.auth.entities.Permission;
import com.example.springbootlearning.user.entities.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
    extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

  @Query("SELECT p FROM Permission p " +
      "JOIN UserPermission up ON p.id = up.permission.id " +
      "WHERE up.user.id= :userId")
  List<Permission> findPermissionsByUserId(@Param("userId") String userId);
}
