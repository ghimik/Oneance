package com.petproject.oneance.model;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    public enum TransactionType {
        BUY,
        SELL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transactions_transaction_id_seq", allocationSize = 1)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Column(name = "shares", nullable = false)
    private Long shares;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;
}


