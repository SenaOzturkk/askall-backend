package com.askall.modal;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "message_credits")
public class MessageCredit {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "credits", nullable = false)
    private Integer credits = 0;

    // 🟢 Getter & Setter Metotları
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
