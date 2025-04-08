package com.askall.repository;

import com.askall.modal.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByUserId(UUID userId);

    Question findByUserIdAndQuestionId(UUID userId, UUID questionId);

    List<Question> findByVisibility(Question.Visibility visibility);

    List<Question> findByExpiresAtBeforeAndVisibility(Instant expiresAt, Question.Visibility visibility);

    List<Question> findByUserIdAndIsDeletedFalse(UUID userId);
}
