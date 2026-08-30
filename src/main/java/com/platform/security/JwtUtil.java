package com.platform.security;

import com.platform.rbac.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${app.jwt-secret}")
  private String jwtSecret;

  @Value("${app.jwt-expiry-minutes}")
  private long expiryMinutes;

  private SecretKey getKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(String email, Role role, String tenantId) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + expiryMinutes * 60 * 1000);
    return Jwts.builder()
        .subject(email)
        .claim("role", role.name())
        .claim("tenantId", tenantId)
        .issuedAt(now)
        .expiration(expiry)
        .signWith(getKey())
        .compact();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  public String getEmail(String token) {
    return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload()
        .getSubject();
  }

  public String getRole(String token) {
    return (String)
        Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload()
            .get("role");
  }

  public String getTenantId(String token) {
    return (String)
        Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload()
            .get("tenantId");
  }
}
