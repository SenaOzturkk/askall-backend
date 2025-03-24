package com.askall.repository;

import com.askall.modal.LikeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface LikeQuestionRepository extends JpaRepository<LikeQuestion, UUID> {

    // Belirli bir soruya yapılan beğenileri getir
    List<LikeQuestion> findByQuestionId(UUID questionId);

    // Belirli bir kullanıcının beğendiği soruları getir
    List<LikeQuestion> findByUserId(UUID userId);

    // Kullanıcının bir soruyu beğenip beğenmediğini kontrol et
    boolean existsByUserIdAndQuestionId(UUID userId, UUID questionId);

    // Kullanıcının bir sorudan beğenisini kaldırması için
    void deleteByUserIdAndQuestionId(UUID userId, UUID questionId);
}
