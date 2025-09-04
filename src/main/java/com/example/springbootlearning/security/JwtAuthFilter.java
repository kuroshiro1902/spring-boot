package com.example.springbootlearning.security;

import com.example.springbootlearning.user.*;
import com.example.springbootlearning.user.dto.requests.FindRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response); // không có token → cho qua (sẽ bị 401 ở chỗ khác)
      return;
    }

    String token = authHeader.substring(7);
    String id = jwtService.extractId(token);

    if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      FindRequest userFindRequest = new FindRequest();
      userFindRequest.setId(id);
      User user = userService.findOneOrThrow(userFindRequest);

      if (jwtService.isTokenValid(token, user)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                user,
                null
            );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
