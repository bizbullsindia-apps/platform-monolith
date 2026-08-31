package com.platform.category;

import com.platform.tenant.TenantContext;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;
    public CategoryService(CategoryRepository repo){ this.repo = repo; }

    public List<Category> listAll(){
        String tenant = TenantContext.getCurrentTenant();
        if(tenant == null) tenant = "default";
        return repo.findByTenantIdOrderBySortOrderAsc(tenant);
    }

    public Category create(Category cat){
        cat.tenantId = TenantContext.getCurrentTenant();
        if(cat.slug == null && cat.name != null){
            cat.slug = cat.name.toLowerCase().replaceAll("[^a-z0-9]+","-");
        }
        return repo.save(cat);
    }
}