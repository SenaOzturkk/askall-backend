package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "likes_questions")
public class LikeQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_id", nullable = false, updatable = false)
    private UUID likeId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
