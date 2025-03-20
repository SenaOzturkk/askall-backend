package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "blocked_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"blocker_id", "blocked_id"})
})
public class BlockedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "blocker_id", nullable = false)
    private UUID blockerId;

    @Column(name = "blocked_id", nullable = false)
    private UUID blockedId;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();
}
