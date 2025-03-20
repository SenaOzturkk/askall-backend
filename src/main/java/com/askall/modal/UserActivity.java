package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id", nullable = false, updatable = false)
    private UUID activityId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    public enum Action {
        LOGIN, LOGOUT, ASK_QUESTION, ANSWER_QUESTION, LIKE, FOLLOW, BLOCK, REPORT
    }
}
