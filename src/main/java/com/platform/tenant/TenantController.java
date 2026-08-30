package com.platform.tenant;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

  @Autowired private TenantService tenantService;
  @Autowired private TenantRepository tenantRepository;

  @GetMapping
  public List<Tenant> list() {
    return tenantRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable String id) {
    var t = tenantRepository.findById(id);
    if (t.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(t.get());
  }

  @PostMapping
  @PreAuthorize("hasAuthority('SUPER_ADMIN')")
  public ResponseEntity<?> create(@RequestBody Tenant tenant) {
    if (tenantRepository.existsById(tenant.getId())) {
      return ResponseEntity.badRequest().body("Tenant already exists");
    }
    var saved = tenantRepository.save(tenant);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('SUPER_ADMIN')")
  public ResponseEntity<?> delete(@PathVariable String id) {
    if (!tenantRepository.existsById(id)) return ResponseEntity.notFound().build();
    tenantRepository.deleteById(id);
    return ResponseEntity.ok("Deleted");
  }
}
