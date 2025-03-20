package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id", nullable = false, updatable = false)
    private UUID answerId;

    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "answer_text", nullable = false, columnDefinition = "TEXT")
    private String answerText;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
