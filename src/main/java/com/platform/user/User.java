package com.platform.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.rbac.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id @GeneratedValue private UUID id;
  @Column(unique = true, nullable = false) private String email;
  @JsonIgnore @Column(nullable = false) private String password;
  @Column(nullable = false) private String fullName;
  @Enumerated(EnumType.STRING) @Column(nullable = false) private Role role;
  @Column(nullable = false) private String tenantId;
  @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }
  public String getTenantId() { return tenantId; }
  public void setTenantId(String tenantId) { this.tenantId = tenantId; }
  public LocalDateTime getCreatedAt() { return createdAt; }
}
