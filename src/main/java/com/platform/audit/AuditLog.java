package com.platform.audit;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue
    public UUID id;

    @Column(name="tenant_id")
    public String tenantId;

    public String action; // CREATE_TENANT, LOGIN, UPLOAD_FILE
    public String entityName;
    public String entityId;
    public String performedBy; // email from JWT
    public Instant timestamp = Instant.now();

    @Column(columnDefinition="TEXT")
    public String details;
}