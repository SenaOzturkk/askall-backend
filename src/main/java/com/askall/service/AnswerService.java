package com.askall.service;

import com.askall.modal.Answer;

import com.askall.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    // Tüm cevapları getir (sadece silinmemişleri)
    public List<Answer> getAllAnswers() {
        return answerRepository.findByIsDeletedFalse();
    }

    // Belirli bir ID'ye göre cevabı getir
    public Optional<Answer> getAnswerById(UUID answerId) {
        return answerRepository.findById(answerId);
    }

    // Belirli bir soruya ait tüm cevapları getir
    public List<Answer> getAnswersByQuestionId(UUID questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    // Yeni bir cevap oluştur
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    // Cevabı güncelle
    public Answer updateAnswer(UUID answerId, Answer updatedAnswer) {
        return answerRepository.findById(answerId).map(answer -> {
            answer.setAnswerText(updatedAnswer.getAnswerText());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Cevap bulunamadı!"));
    }

    // Cevabı sil (Soft Delete)
    public void deleteAnswer(UUID answerId) {
        answerRepository.findById(answerId).ifPresent(answer -> {
            answer.setIsDeleted(true);
            answerRepository.save(answer);
        });
    }
}
