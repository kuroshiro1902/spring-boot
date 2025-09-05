package com.example.springbootlearning.security;

import com.example.springbootlearning.user.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String SECRET_KEY;

  {
    System.out.println(this.SECRET_KEY);
  }

  private boolean isTokenExpired(String token) {
    return extractClaim(token, Claims::getExpiration).before(new Date());
  }

  public String extractId(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println("SECRET_KEY: " + this.SECRET_KEY);
  }

  public <T> T extractClaim(String token, Function<Claims, T> resolver) {
    final Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return resolver.apply(claims);
  }

  public boolean isTokenValid(String token, User user) {
    final String id = extractId(token);
    return id.equals(user.getId()) && !isTokenExpired(token);
  }

  public String generateToken(User user) {
    return Jwts.builder()
        .setSubject(user.getId())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }
}
