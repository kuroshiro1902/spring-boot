package com.example.springbootlearning.user.controller;

import com.example.springbootlearning.user.dto.UserWithoutPassword;
import com.example.springbootlearning.user.dto.input.SearchUserInput;
import com.example.springbootlearning.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/search")
  public Page<UserWithoutPassword> search(@Valid SearchUserInput input) {
    return userService.search(input);
  }

}
