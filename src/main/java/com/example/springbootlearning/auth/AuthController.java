package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.requests.SignupRequest;
import com.example.springbootlearning.auth.dto.responses.SignupResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public SignupResponse signup(@Valid @RequestBody SignupRequest request) {
    return authService.signup(request);
  }
}
