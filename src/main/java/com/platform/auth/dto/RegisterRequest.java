package com.platform.auth.dto;
public class RegisterRequest {
  private String email;
  private String password;
  private String fullName;
  private String role;
  private String tenantId;
  public String getEmail() { return email; }
  public void setEmail(String e) { this.email = e; }
  public String getPassword() { return password; }
  public void setPassword(String p) { this.password = p; }
  public String getFullName() { return fullName; }
  public void setFullName(String f) { this.fullName = f; }
  public String getRole() { return role; }
  public void setRole(String r) { this.role = r; }
  public String getTenantId() { return tenantId; }
  public void setTenantId(String t) { this.tenantId = t; }
}
