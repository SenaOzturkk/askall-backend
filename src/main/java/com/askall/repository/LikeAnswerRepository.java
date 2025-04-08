package com.askall.repository;

import com.askall.modal.LikeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeAnswerRepository extends JpaRepository<LikeAnswer, UUID> {

    // Belirli bir cevabı beğenen kullanıcıları listele
    Optional<LikeAnswer> findByAnswerId(UUID answerId);

    // Belirli bir kullanıcının beğendiği cevapları listele
    Optional<LikeAnswer> findByUserId(UUID userId);

    // Kullanıcının bir cevabı beğenip beğenmediğini kontrol et
    boolean existsByUserIdAndAnswerId(UUID userId, UUID answerId);

    Optional<LikeAnswer> findByUserIdAndAnswerId(UUID userId, UUID answerId);

    // Kullanıcının bir cevaptan beğenisini kaldırması için
    void deleteByUserIdAndAnswerId(UUID userId, UUID answerId);
}
