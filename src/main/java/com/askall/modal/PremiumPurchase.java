package com.askall.modal;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "premium_purchases")
public class PremiumPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_id", nullable = false, updatable = false)
    private UUID purchaseId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "purchase_date", nullable = false)
    private Instant purchaseDate = Instant.now();

    @Column(name = "expiration_date", nullable = false)
    private Instant expirationDate;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "purchase_type", nullable = false, length = 50)
    private String purchaseType;
}
