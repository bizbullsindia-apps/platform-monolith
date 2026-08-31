package com.platform.audit;

import com.platform.tenant.TenantContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
    private final AuditLogRepository repo;
    public AuditController(AuditLogRepository repo){ this.repo = repo; }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','PLATFORM_EMPLOYEE','APP_OWNER')")
    public List<AuditLog> list(){
        String tenant = TenantContext.getCurrentTenant();
        if(tenant == null || tenant.equals("default")){
            return repo.findAll();
        }
        return repo.findByTenantIdOrderByTimestampDesc(tenant);
    }
}