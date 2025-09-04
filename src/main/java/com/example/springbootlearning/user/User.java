package com.example.springbootlearning.user;

import com.example.springbootlearning.base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Base {
  @Column(name = "username", nullable = false, unique = true, length = 255)
  String username;

  @Column (name = "password", nullable = false, columnDefinition = "TEXT")
  String password;

  @Column(name = "name", nullable = false, length = 255)
  String name;

  @Column(name = "email", nullable = false, unique = true, length = 255)
  String email;
}
