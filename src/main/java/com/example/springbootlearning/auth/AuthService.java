package com.example.springbootlearning.auth;

import com.example.springbootlearning.auth.dto.AuthDtoMapper;
import com.example.springbootlearning.auth.dto.input.LoginInput;
import com.example.springbootlearning.auth.dto.input.SignupInput;
import com.example.springbootlearning.auth.dto.LoginResponse;
import com.example.springbootlearning.auth.dto.SignupResponse;
import com.example.springbootlearning.common.exception.UnauthorizedException;
import com.example.springbootlearning.security.JwtService;
import com.example.springbootlearning.user.entity.User;
import com.example.springbootlearning.user.service.UserService;
import com.example.springbootlearning.user.dto.input.FindUserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthDtoMapper authDtoMapper;
  private final JwtService jwtService;

  public SignupResponse signup(SignupInput request) {
    String hashedPassword = passwordEncoder.encode(request.password());

    User user = authDtoMapper.toUserEntity(request);
    user.setPassword(hashedPassword);

    User savedUser = userService.createOne(user);

    return authDtoMapper.toSignupResponse(savedUser);
  }

  public LoginResponse login(LoginInput request) {
    String username = request.username();
    String password = request.password();
    FindUserInput findUserInput = new FindUserInput();
    findUserInput.setUsername(username);
    User user = userService.findOneOrThrow(findUserInput);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UnauthorizedException("Password is incorrect");
    }

    String accessToken = jwtService.generateToken(user);
    return authDtoMapper.toLoginResponse(user, accessToken);
  }
}
