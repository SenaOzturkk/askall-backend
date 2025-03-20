package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "likes_answers")
public class LikeAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_id", nullable = false, updatable = false)
    private UUID likeId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "answer_id", nullable = false)
    private UUID answerId;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();
}
