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

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Getter ve Setter metotları
    public UUID getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
