package com.example.springbootlearning.user.entities;

import com.example.springbootlearning.auth.entities.Permission;
import com.example.springbootlearning.common.entities.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_permissions", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "permission_id"}))
@Getter
@Setter
public class UserPermission extends Base {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_id", nullable = false)
  Permission permission;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_by_id", insertable = false)
  User assignedBy;
}
