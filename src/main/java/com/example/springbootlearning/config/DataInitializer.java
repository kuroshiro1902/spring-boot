package com.example.springbootlearning.config;

import com.example.springbootlearning.auth.entities.Permission;
import com.example.springbootlearning.auth.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final PermissionService permissionService;

  @Override
  public void run(String... args) {
    permissionService.findOneByCode("default_user")
        .orElseGet(() -> {
          Permission p = new Permission();
          p.setName("Default User");
          p.setCode("default_user");
          p.setDescription("Default permission for all new users");
          return permissionService.createOne(p);
        });
  }
}
