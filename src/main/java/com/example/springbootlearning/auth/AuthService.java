package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.AuthMapper;
import com.example.springbootlearning.auth.dto.requests.LoginRequest;
import com.example.springbootlearning.auth.dto.requests.SignupRequest;
import com.example.springbootlearning.auth.dto.responses.LoginResponse;
import com.example.springbootlearning.auth.dto.responses.SignupResponse;
import com.example.springbootlearning.common.exceptions.UnauthorizedException;
import com.example.springbootlearning.security.JwtService;
import com.example.springbootlearning.user.User;
import com.example.springbootlearning.user.UserService;
import com.example.springbootlearning.user.dto.requests.FindUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthMapper authMapper;
  private final JwtService jwtService;

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
    FindUserRequest findUserRequest = new FindUserRequest();
    findUserRequest.setUsername(username);
    User user = userService.findOneOrThrow(findUserRequest);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UnauthorizedException("Password is incorrect");
    }

    String accessToken = jwtService.generateToken(user);
    return authMapper.toLoginResponse(user, accessToken);
  }
}
