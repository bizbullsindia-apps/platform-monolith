package com.platform.listing;

import com.platform.common.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="listings")
public class Listing extends BaseEntity {
    public String title;
    @Column(columnDefinition="TEXT")
    public String description;
    public BigDecimal price;
    public String categoryId;
    @Column(columnDefinition="TEXT")
    public String images; // JSON array of URLs
    public String location;
    public String status = "ACTIVE";
    public String createdBy;

    @Column(columnDefinition="TEXT")
    public String attributes; // JSON flexible per app: {"bedrooms":2, "area":1200}
}