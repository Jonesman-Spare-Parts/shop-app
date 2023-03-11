package com.jonesman.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "daily_totals")
public class DailyTotalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "total_units", nullable = false)
    private int totalUnits;

    public DailyTotalEntity() {
    }

    public DailyTotalEntity(LocalDate date, ProductEntity product, int totalUnits) {
        this.date = date;
        this.product = product;
        this.totalUnits = totalUnits;
    }

    // getters and setters
}


