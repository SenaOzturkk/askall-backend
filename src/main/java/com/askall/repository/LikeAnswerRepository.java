package com.askall.repository;

import com.askall.modal.LikeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface LikeAnswerRepository extends JpaRepository<LikeAnswer, UUID> {

    // Belirli bir cevabı beğenen kullanıcıları listele
    List<LikeAnswer> findByAnswerId(UUID answerId);

    // Belirli bir kullanıcının beğendiği cevapları listele
    List<LikeAnswer> findByUserId(UUID userId);

    // Kullanıcının bir cevabı beğenip beğenmediğini kontrol et
    boolean existsByUserIdAndAnswerId(UUID userId, UUID answerId);

    // Kullanıcının bir cevaptan beğenisini kaldırması için
    void deleteByUserIdAndAnswerId(UUID userId, UUID answerId);
}
