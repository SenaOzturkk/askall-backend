package com.askall.repository;

import com.askall.modal.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    // Kullanıcının tüm sorularını getir
    List<Question> findByUserId(UUID userId);

    // Kullanıcının belirli bir sorusunu getir
    Question findByUserIdAndQuestionId(UUID userId, UUID questionId);

    // Görünürlüğe göre soruları getir
    List<Question> findByVisibility(Question.Visibility visibility);

    // Bitiş tarihi geçmiş soruları getir
    List<Question> findByExpiresAtBeforeAndVisibility(Instant expiresAt, Question.Visibility visibility);
}
