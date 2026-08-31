package com.platform.category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryRepository repo;
    public CategoryController(CategoryService service, CategoryRepository repo){
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public List<Category> list(){ return service.listAll(); }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','APP_OWNER','APP_OWNER_EMPLOYEE')")
    public Category create(@RequestBody Category cat){ return service.create(cat); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','APP_OWNER')")
    public void delete(@PathVariable UUID id){ repo.deleteById(id); }
}