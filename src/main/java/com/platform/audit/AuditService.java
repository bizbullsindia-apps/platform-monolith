package com.platform.audit;

import com.platform.tenant.TenantContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository repo;
    public AuditService(AuditLogRepository repo){ this.repo = repo; }

    public void log(String action, String entityName, String entityId, String details){
        String tenant = TenantContext.getCurrentTenant();
        String user = "system";
        try{
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null) user = auth.getName();
        }catch(Exception e){}

        AuditLog log = new AuditLog();
        log.tenantId = tenant;
        log.action = action;
        log.entityName = entityName;
        log.entityId = entityId;
        log.performedBy = user;
        log.details = details;
        repo.save(log);
    }
}