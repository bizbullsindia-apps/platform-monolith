package com.platform.auth;

import com.platform.auth.dto.*;
import com.platform.rbac.Role;
import com.platform.security.JwtUtil;
import com.platform.tenant.TenantContext;
import com.platform.user.User;
import com.platform.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
    if (userRepository.existsByEmail(req.getEmail())) {
      return ResponseEntity.badRequest().body("Email already exists");
    }
    String tenantId = req.getTenantId();
    if (tenantId == null || tenantId.isBlank()) {
      tenantId = TenantContext.getTenantId();
      if (tenantId == null || tenantId.isBlank()) tenantId = "default";
    }
    Role role;
    try {
      role = req.getRole() != null ? Role.valueOf(req.getRole()) : Role.COMMON_USER;
    } catch (Exception e) {
      role = Role.COMMON_USER;
    }

    User user = new User();
    user.setEmail(req.getEmail());
    user.setPassword(passwordEncoder.encode(req.getPassword()));
    user.setFullName(req.getFullName() != null ? req.getFullName() : req.getEmail());
    user.setRole(role);
    user.setTenantId(tenantId);
    userRepository.save(user);

    String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getTenantId());
    return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), role.name(), tenantId));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    var userOpt = userRepository.findByEmail(req.getEmail());
    if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid credentials");
    var user = userOpt.get();
    if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
    String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getTenantId());
    return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole().name(), user.getTenantId()));
  }

  @GetMapping("/me")
  public ResponseEntity<?> me() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || auth.getName() == null) return ResponseEntity.status(401).body("Not authenticated");
    var userOpt = userRepository.findByEmail(auth.getName());
    if (userOpt.isEmpty()) return ResponseEntity.status(404).body("User not found");
    var user = userOpt.get();
    return ResponseEntity.ok(user);
  }
}
