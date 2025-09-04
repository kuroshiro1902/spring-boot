package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.AuthMapper;
import com.example.springbootlearning.auth.dto.requests.LoginRequest;
import com.example.springbootlearning.auth.dto.requests.SignupRequest;
import com.example.springbootlearning.auth.dto.responses.LoginResponse;
import com.example.springbootlearning.auth.dto.responses.SignupResponse;
import com.example.springbootlearning.common.exceptions.UnauthorizedException;
import com.example.springbootlearning.user.User;
import com.example.springbootlearning.user.UserService;
import com.example.springbootlearning.user.dto.requests.FindRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthMapper authMapper;

  public SignupResponse signup(SignupRequest request) {
    String hashedPassword = passwordEncoder.encode(request.password());

    User user = authMapper.toUserEntity(request);
    user.setPassword(hashedPassword);

    User savedUser = userService.create(user);

    return authMapper.toSignupResponse(savedUser);
  }

  public LoginResponse login(LoginRequest request) {
    String username = request.username();
    String password = request.password();
    FindRequest findRequest = new FindRequest();
    findRequest.setUsername(username);
    User user = userService.findOneOrThrow(findRequest);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UnauthorizedException("Password is incorrect");
    }

    return authMapper.toLoginResponse(user);
  }
}
