package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_premium_status")
public class UserPremiumStatus {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "is_premium", nullable = false)
    private Boolean isPremium = false;

    @Column(name = "expiration_date", nullable = false)
    private Instant expirationDate;

    @Column(name = "message_rights_left", nullable = false)
    private Integer messageRightsLeft = 0;

    @Column(name = "question_rights_left", nullable = false)
    private Integer questionRightsLeft = 0;

    // Getter ve Setter metotları

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getMessageRightsLeft() {
        return messageRightsLeft;
    }

    public void setMessageRightsLeft(Integer messageRightsLeft) {
        this.messageRightsLeft = messageRightsLeft;
    }

    public Integer getQuestionRightsLeft() {
        return questionRightsLeft;
    }

    public void setQuestionRightsLeft(Integer questionRightsLeft) {
        this.questionRightsLeft = questionRightsLeft;
    }
}
