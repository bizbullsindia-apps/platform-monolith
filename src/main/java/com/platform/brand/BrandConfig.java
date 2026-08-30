package com.platform.brand;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brand_configs")
public class BrandConfig {
  @Id private String tenantId;
  @Column(nullable = false) private String brandName;
  private String primaryColor = "#0F172A";
  private String logoUrl;
  private String domain;
  @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();

  public String getTenantId() { return tenantId; }
  public void setTenantId(String t) { this.tenantId = t; }
  public String getBrandName() { return brandName; }
  public void setBrandName(String b) { this.brandName = b; }
  public String getPrimaryColor() { return primaryColor; }
  public void setPrimaryColor(String c) { this.primaryColor = c; }
  public String getLogoUrl() { return logoUrl; }
  public void setLogoUrl(String l) { this.logoUrl = l; }
  public String getDomain() { return domain; }
  public void setDomain(String d) { this.domain = d; }
  public LocalDateTime getCreatedAt() { return createdAt; }
}
