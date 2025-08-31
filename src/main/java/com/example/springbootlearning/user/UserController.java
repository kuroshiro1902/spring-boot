package com.example.springbootlearning.user;

import com.example.springbootlearning.user.dto.requests.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/search")
  public Page<User> search(@RequestParam Optional<String> name, @RequestParam Optional<String> email, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam Optional<String> sortBy) {
    return userService.search(name, email, page, size, sortBy);
  }

  @PostMapping
  public User create(@Valid @RequestBody CreateUserRequest request) {
    return userService.create(request);
  }
}
