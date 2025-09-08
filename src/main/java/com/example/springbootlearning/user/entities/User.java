package com.example.springbootlearning.user.entities;

import com.example.springbootlearning.auth.entities.UserPermission;
import com.example.springbootlearning.auth.entities.UserRole;
import com.example.springbootlearning.common.entities.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Base {
  @Column(name = "username", nullable = false, unique = true)
  String username;

  @Column (name = "password", nullable = false, columnDefinition = "TEXT")
  String password;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "email", nullable = false, unique = true)
  String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserRole> userRoles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserPermission> userPermissions = new HashSet<>();
}
