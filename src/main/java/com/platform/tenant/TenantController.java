package com.platform.tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List; import java.util.Map;
@RestController @RequestMapping("/api/tenants")
public class TenantController {
  @Autowired private TenantService service;
  @GetMapping public List<Tenant> list(){return service.list();}
  @PostMapping public Tenant create(@RequestBody Tenant t){return service.create(t);}
  @GetMapping("/current") public Map<String,String> current(){
    return Map.of("tenantId", TenantContext.getTenantId()!=null?TenantContext.getTenantId():"default");
  }
}
