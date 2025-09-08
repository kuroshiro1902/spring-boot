package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.request.*;
import com.example.springbootlearning.auth.dto.response.*;
import com.example.springbootlearning.common.dto.response.ResponseData;
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
  public SignupResponse signup(@Valid @RequestBody SignupRequest request) {
    return authService.signup(request);
  }

  @PostMapping("/login")
  public ResponseData<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseData.success(authService.login(request));
  }
}
