package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Question List successfully", questions);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createQuestion(@RequestBody  Question question) {
        ApiResponse<Object>  savedQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(savedQuestion.getStatus()).body(savedQuestion);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsByUser(@PathVariable UUID userId) {
        ApiResponse<List<Question>> response = questionService.getQuestionsByUser(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{userId}/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable UUID userId, @PathVariable UUID questionId) {
        Question question = questionService.getQuestion(userId, questionId);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/visibility/{visibility}")
    public ResponseEntity<ApiResponse<Object>>  getQuestionsByVisibility(@PathVariable Question.Visibility visibility) {
        List<Question> questions = questionService.getQuestionsByVisibility(visibility);
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Question visibility list successfully", questions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/expired/{visibility}")
    public ResponseEntity<ApiResponse<Object>> getExpiredQuestions(@PathVariable Question.Visibility visibility) {
        Instant now = Instant.now();
        List<Question> expiredQuestions = questionService.getExpiredQuestions(now, visibility);
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Question expired list successfully", expiredQuestions);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<ApiResponse<Object>>  deleteQuestion(@PathVariable UUID questionId) {
        Optional<Question> deletedQuestion = questionService.deleteQuestion(questionId);
        try {
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Question deleted successfully", deletedQuestion.get());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "Question not found: " + questionId);
            return ResponseEntity.ok(response);
        }
    }
}
