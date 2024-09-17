package com.petproject.oneance.model;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "companies_company_id_seq", allocationSize = 1)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ticker", nullable = false, unique = true)
    private String ticker;

    @Column(name = "capitalization")
    private BigDecimal capitalization;

    @Column(name = "total_shares", nullable = false)
    private Long totalShares;

    @Column(name = "available_shares", nullable = false)
    private Long availableShares;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockPriceHistory> priceHistory;
}
