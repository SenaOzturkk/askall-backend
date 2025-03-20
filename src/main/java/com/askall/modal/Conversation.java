package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conversation_id", nullable = false, updatable = false)
    private UUID conversationId;

    @Column(name = "user1_id", nullable = false)
    private UUID user1Id;

    @Column(name = "user2_id", nullable = false)
    private UUID user2Id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();
}
