package com.petproject.oneance.model;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_price_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id_seq")
    @SequenceGenerator(name = "history_id_seq", sequenceName = "stock_price_history_history_id_seq", allocationSize = 1)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "recorded_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime recordedAt;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "volume")
    private Long volume;
}

