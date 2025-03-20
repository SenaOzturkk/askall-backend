package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", nullable = false, updatable = false)
    private UUID questionId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Column(name = "question_image", columnDefinition = "TEXT")
    private String questionImage;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility", nullable = false)
    private Visibility visibility;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "radius")
    private Double radius;

    public enum Visibility {
        FOLLOWERS, FOLLOWING, PUBLIC
    }
}
