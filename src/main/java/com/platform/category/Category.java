package com.platform.category;

import com.platform.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name="categories", uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id","slug"}))
public class Category extends BaseEntity {
    public String name;
    public String slug;
    public String description;
    public String parentId;
    public String imageUrl;
    public boolean active = true;
    public int sortOrder = 0;
}