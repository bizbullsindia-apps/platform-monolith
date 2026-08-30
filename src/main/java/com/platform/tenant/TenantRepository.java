package com.platform.tenant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TenantRepository extends JpaRepository<Tenant, String> {
  java.util.Optional<Tenant> findByDomain(String domain);
}
