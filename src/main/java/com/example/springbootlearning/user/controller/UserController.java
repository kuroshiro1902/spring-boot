package com.example.springbootlearning.user.controller;

import com.example.springbootlearning.user.entity.User;
import com.example.springbootlearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/search")
  public Page<User> search(@RequestParam Optional<String> name, @RequestParam Optional<String> email, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam Optional<String> sortBy) {
    return userService.search(name, email, page, size, sortBy);
  }

}
