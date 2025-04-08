package com.askall.service;

import com.askall.dto.ApiResponse;
import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.repository.QuestionRepository;
import com.askall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ApiResponse<Object>  createQuestion(Question question) {
        if (question.getUserId() == null) {
            return ApiResponse.success(HttpStatus.OK, "User Id is required", null);
        }

        if (question.getQuestionText() == null || question.getQuestionText().isEmpty()) {
            return ApiResponse.success(HttpStatus.OK, "Question text cannot be empty.", null);
        }

        if (question.getQuestionImage() == null || question.getQuestionImage().isEmpty()) {
            question.setQuestionImage(null);
        }

        if (question.getLatitude() == null || question.getLongitude() == null) {
            return ApiResponse.success(HttpStatus.OK, "Latitude and Longitude cannot be null.", null);
        }

        if (question.getRadius() <= 0) {
            return ApiResponse.success(HttpStatus.OK, "Radius must be greater than 0", null);
        }

        if (question.getExpiresAt() == null || question.getExpiresAt().isBefore(Instant.now())) {
            return ApiResponse.success(HttpStatus.OK, "Expiration date cannot be in the past.", null);
        }

        Question savedQuestion = questionRepository.save(question);
        return ApiResponse.success(HttpStatus.OK, "Question created successfully", savedQuestion);
    }

    public ApiResponse<List<Question>> getQuestionsByUser(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ApiResponse.success(HttpStatus.OK, "User not found",null);
        }
        List<Question> savedQuestion = questionRepository.findByUserIdAndIsDeletedFalse(userId);
        return ApiResponse.success(HttpStatus.OK, "User questions found successfully", savedQuestion);
    }

    public Question getQuestion(UUID userId, UUID questionId) {
        return questionRepository.findByUserIdAndQuestionId(userId, questionId);
    }

    public List<Question> getQuestionsByVisibility(Question.Visibility visibility) {
        return questionRepository.findByVisibility(visibility);
    }

    public List<Question> getExpiredQuestions(Instant now, Question.Visibility visibility) {
        return questionRepository.findByExpiresAtBeforeAndVisibility(now, visibility);
    }

    public Optional<Question>  deleteQuestion(UUID questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        question.ifPresent(questionRepository::delete);
        return question;
    }

}
