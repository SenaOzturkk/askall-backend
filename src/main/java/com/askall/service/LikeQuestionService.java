package com.askall.service;

import com.askall.modal.LikeQuestion;
import com.askall.repository.LikeQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LikeQuestionService {

    private final LikeQuestionRepository likeQuestionRepository;

    public LikeQuestionService(LikeQuestionRepository likeQuestionRepository) {
        this.likeQuestionRepository = likeQuestionRepository;
    }

    // Soruyu beğenme
    public LikeQuestion likeQuestion(UUID userId, UUID questionId) {
        if (likeQuestionRepository.existsByUserIdAndQuestionId(userId, questionId)) {
            throw new IllegalArgumentException("Bu soru zaten beğenildi.");
        }

        LikeQuestion likeQuestion = new LikeQuestion();
        likeQuestion.setUserId(userId);
        likeQuestion.setQuestionId(questionId);
        return likeQuestionRepository.save(likeQuestion);
    }

    // Beğeniyi kaldırma
    public void unlikeQuestion(UUID userId, UUID questionId) {
        if (!likeQuestionRepository.existsByUserIdAndQuestionId(userId, questionId)) {
            throw new IllegalArgumentException("Bu soru zaten beğenilmemiş.");
        }

        likeQuestionRepository.deleteByUserIdAndQuestionId(userId, questionId);
    }

    // Belirli bir soruyu beğenen kullanıcıları getir
    public List<LikeQuestion> getLikesByQuestionId(UUID questionId) {
        return likeQuestionRepository.findByQuestionId(questionId);
    }

    // Belirli bir kullanıcının beğendiği soruları getir
    public List<LikeQuestion> getLikesByUserId(UUID userId) {
        return likeQuestionRepository.findByUserId(userId);
    }
}
