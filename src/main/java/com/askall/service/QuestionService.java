package com.askall.service;

import com.askall.modal.Question;
import com.askall.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Yeni bir soru oluştur
    public Question createQuestion(UUID userId, String questionText, String questionImage, Double latitude,
                                   Double longitude, Question.Visibility visibility, Instant expiresAt, Double radius) {
        Question question = new Question();
        question.setUserId(userId);
        question.setQuestionText(questionText);
        question.setQuestionImage(questionImage);
        question.setLatitude(latitude);
        question.setLongitude(longitude);
        question.setVisibility(visibility);
        question.setExpiresAt(expiresAt);
        question.setRadius(radius);
        return questionRepository.save(question);
    }

    // Kullanıcının tüm sorularını listele
    public List<Question> getQuestionsByUser(UUID userId) {
        return questionRepository.findByUserId(userId);
    }

    // Kullanıcının belirli bir sorusunu getir
    public Question getQuestion(UUID userId, UUID questionId) {
        return questionRepository.findByUserIdAndQuestionId(userId, questionId);
    }

    // Görünürlüğe göre soruları listele
    public List<Question> getQuestionsByVisibility(Question.Visibility visibility) {
        return questionRepository.findByVisibility(visibility);
    }

    // Geçerliliği bitmiş soruları getir
    public List<Question> getExpiredQuestions(Instant now, Question.Visibility visibility) {
        return questionRepository.findByExpiresAtBeforeAndVisibility(now, visibility);
    }
}
