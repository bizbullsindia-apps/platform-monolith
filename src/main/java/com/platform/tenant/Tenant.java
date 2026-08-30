package com.platform.tenant;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name = "tenants")
public class Tenant {
  @Id private String id;
  @Column(nullable = false) private String name;
  @Column(unique = true) private String domain;
  @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
  public String getId(){return id;} public void setId(String id){this.id=id;}
  public String getName(){return name;} public void setName(String name){this.name=name;}
  public String getDomain(){return domain;} public void setDomain(String domain){this.domain=domain;}
  public LocalDateTime getCreatedAt(){return createdAt;}
}
