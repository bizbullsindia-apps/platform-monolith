package com.platform.common;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    public UUID id;
    
    @Column(name="tenant_id")
    public String tenantId;
    
    public Instant createdAt = Instant.now();
    public Instant updatedAt = Instant.now();
    
    @PreUpdate
    public void preUpdate(){ updatedAt = Instant.now(); }
}