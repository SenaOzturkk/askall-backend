package com.askall.service;

import com.askall.modal.LikeAnswer;
import com.askall.repository.LikeAnswerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LikeAnswerService {

    private final LikeAnswerRepository likeAnswerRepository;

    public LikeAnswerService(LikeAnswerRepository likeAnswerRepository) {
        this.likeAnswerRepository = likeAnswerRepository;
    }

    // Cevaba beğeni ekle
    public LikeAnswer likeAnswer(UUID userId, UUID answerId) {
        if (likeAnswerRepository.existsByUserIdAndAnswerId(userId, answerId)) {
            throw new IllegalArgumentException("Bu cevap zaten beğenildi.");
        }

        LikeAnswer likeAnswer = new LikeAnswer();
        likeAnswer.setUserId(userId);
        likeAnswer.setAnswerId(answerId);
        return likeAnswerRepository.save(likeAnswer);
    }

    // Cevaptan beğeniyi kaldır
    public void unlikeAnswer(UUID userId, UUID answerId) {
        if (!likeAnswerRepository.existsByUserIdAndAnswerId(userId, answerId)) {
            throw new IllegalArgumentException("Bu cevap zaten beğenilmemiş.");
        }

        likeAnswerRepository.deleteByUserIdAndAnswerId(userId, answerId);
    }

    // Belirli bir cevabı beğenen kullanıcıları getir
    public List<LikeAnswer> getLikesByAnswerId(UUID answerId) {
        return likeAnswerRepository.findByAnswerId(answerId);
    }

    // Belirli bir kullanıcının beğendiği cevapları getir
    public List<LikeAnswer> getLikesByUserId(UUID userId) {
        return likeAnswerRepository.findByUserId(userId);
    }
}
