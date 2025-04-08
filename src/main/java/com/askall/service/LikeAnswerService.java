package com.askall.service;

import com.askall.modal.Follow;
import com.askall.modal.LikeAnswer;
import com.askall.repository.LikeAnswerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LikeAnswerService {

    private final LikeAnswerRepository likeAnswerRepository;

    public LikeAnswerService(LikeAnswerRepository likeAnswerRepository) {
        this.likeAnswerRepository = likeAnswerRepository;
    }

    public LikeAnswer likeAnswer(UUID userId, UUID answerId) {
        boolean likeAnswer = likeAnswerRepository.existsByUserIdAndAnswerId(userId, answerId);
        if (likeAnswer) {
            throw new IllegalArgumentException("Bu cevap zaten beğenildi.");
        }

        LikeAnswer likeAnswerUpdate = new LikeAnswer();
        likeAnswerUpdate.setUserId(userId);
        likeAnswerUpdate.setAnswerId(answerId);
        return likeAnswerRepository.save(likeAnswerUpdate);
    }

    public Optional<LikeAnswer> unlikeAnswer(UUID userId, UUID answerId) {
        Optional<LikeAnswer> likeAnswer = likeAnswerRepository.findByUserIdAndAnswerId(userId, answerId);
        if (likeAnswer.isEmpty()) {
            throw new RuntimeException("likeAnswer not found");
        }

        LikeAnswer likeAnswerUpdate = likeAnswer.get();
        likeAnswerUpdate.setDeleted(true);
        likeAnswerRepository.save(likeAnswerUpdate);

        return Optional.of(likeAnswerUpdate);
    }

    public Optional<LikeAnswer> getLikesByAnswerId(UUID answerId) {
        return likeAnswerRepository.findByAnswerId(answerId);
    }

    public Optional <LikeAnswer> getLikesByUserId(UUID userId) {
        return likeAnswerRepository.findByUserId(userId);
    }
}
