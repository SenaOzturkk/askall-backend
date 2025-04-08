package com.askall.modal;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ask_credits")
public class AskCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "credits", nullable = false)
    private int credits;

    // ✅ Boş Constructor (JPA için gerekli)
    public AskCredit() {}

    // ✅ Parametreli Constructor
    public AskCredit(UUID userId, int credits) {
        this.userId = userId;
        this.credits = credits;
    }

    // 🔹 Getters ve Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
