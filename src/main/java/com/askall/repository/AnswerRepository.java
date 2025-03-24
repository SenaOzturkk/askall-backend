package com.askall.repository;

import com.askall.modal.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    // Belirli bir soruya ait cevapları getir
    List<Answer> findByQuestionId(UUID questionId);

    // Belirli bir kullanıcının cevaplarını getir
    List<Answer> findByUserId(UUID userId);

    // Silinmemiş cevapları getir
    List<Answer> findByIsDeletedFalse();
}
