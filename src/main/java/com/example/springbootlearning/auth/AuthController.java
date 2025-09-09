package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.LoginResponse;
import com.example.springbootlearning.auth.dto.SignupResponse;
import com.example.springbootlearning.auth.dto.input.*;
import com.example.springbootlearning.common.dto.ResponseData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public SignupResponse signup(@Valid @RequestBody SignupInput input) {
    return authService.signup(input);
  }

  @PostMapping("/login")
  public ResponseData<LoginResponse> login(@Valid @RequestBody LoginInput input) {
    return ResponseData.success(authService.login(input));
  }
}
