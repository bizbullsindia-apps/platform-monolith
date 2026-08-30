package com.platform.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandConfigController {

  @Autowired private BrandConfigRepository brandRepo;

  @GetMapping("/{tenantId}")
  public ResponseEntity<?> getBrand(@PathVariable String tenantId) {
    var brand = brandRepo.findById(tenantId);
    if (brand.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(brand.get());
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','APP_OWNER')")
  public ResponseEntity<?> createBrand(@RequestBody BrandConfig brand) {
    if (brandRepo.existsById(brand.getTenantId())) {
      return ResponseEntity.badRequest().body("Brand already exists for tenant");
    }
    return ResponseEntity.ok(brandRepo.save(brand));
  }

  @PutMapping("/{tenantId}")
  @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','APP_OWNER','PLATFORM_EMPLOYEE')")
  public ResponseEntity<?> updateBrand(@PathVariable String tenantId, @RequestBody BrandConfig updated) {
    var existing = brandRepo.findById(tenantId);
    if (existing.isEmpty()) return ResponseEntity.notFound().build();
    var brand = existing.get();
    if (updated.getBrandName() != null) brand.setBrandName(updated.getBrandName());
    if (updated.getPrimaryColor() != null) brand.setPrimaryColor(updated.getPrimaryColor());
    if (updated.getLogoUrl() != null) brand.setLogoUrl(updated.getLogoUrl());
    if (updated.getDomain() != null) brand.setDomain(updated.getDomain());
    return ResponseEntity.ok(brandRepo.save(brand));
  }
}
