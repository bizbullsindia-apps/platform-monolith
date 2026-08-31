package com.platform.listing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
    List<Listing> findByTenantIdOrderByCreatedAtDesc(String tenantId);
    List<Listing> findByTenantIdAndCategoryIdOrderByCreatedAtDesc(String tenantId, String categoryId);
}