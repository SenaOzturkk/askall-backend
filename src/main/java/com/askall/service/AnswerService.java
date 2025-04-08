package com.askall.service;

import com.askall.dto.ApiResponse;
import com.askall.modal.Answer;

import com.askall.modal.Question;
import com.askall.repository.AnswerRepository;
import org.springframework.http.HttpStatus;
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

    public List<Answer> getAllAnswers() {
        return answerRepository.findByIsDeletedFalse();
    }

    public Optional<Answer> getAnswerById(UUID answerId) {
        return answerRepository.findById(answerId);
    }

    // Belirli bir soruya ait tüm cevapları getir
    public List<Answer> getAnswersByQuestionId(UUID questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    // Yeni bir cevap oluştur
    public ApiResponse<Object> createAnswer(Answer answer) {
        Answer savedAnswer = answerRepository.save(answer);
        return ApiResponse.success(HttpStatus.OK, "Answer created successfully", savedAnswer);
    }

    // Cevabı güncelle
    public Answer updateAnswer(UUID answerId, Answer updatedAnswer) {
        return answerRepository.findById(answerId).map(answer -> {
            answer.setAnswerText(updatedAnswer.getAnswerText());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new RuntimeException("Cevap bulunamadı!"));
    }

    // Cevabı sil (Soft Delete)
    public Optional<Answer> deleteAnswer(UUID answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        answer.ifPresent(answerRepository::delete);
        return answer;
    }
}
