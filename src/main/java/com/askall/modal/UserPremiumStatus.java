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
}
