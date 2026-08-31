package com.platform.category;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByTenantIdOrderBySortOrderAsc(String tenantId);
    List<Category> findByTenantIdAndActiveTrueOrderBySortOrderAsc(String tenantId);
}