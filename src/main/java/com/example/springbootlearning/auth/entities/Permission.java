package com.example.springbootlearning.auth.entities;

import com.example.springbootlearning.common.entities.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission extends Base {
  @Column(nullable = false)
  String name;

  @Column(nullable = false, unique = true)
  String code;

  @Column(length = 1000)
  String description;

  @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<RolePermission> rolePermissions = new HashSet<>();

  @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserPermission> userPermissions = new HashSet<>();
}
