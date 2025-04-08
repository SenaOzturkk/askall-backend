package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Answer;

import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.repository.QuestionRepository;
import com.askall.repository.UserRepository;
import com.askall.service.AnswerService;
import com.askall.service.QuestionService;
import com.askall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Answers List successfully", answers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<ApiResponse<Object>> getAnswerById(@PathVariable UUID answerId) {
        Optional<Answer> answer = answerService.getAnswerById(answerId);

        if (answer.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "Answer found successfully", answer.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "Answer not found: ", null);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<ApiResponse<Object>> getAnswersByQuestionId(@PathVariable UUID questionId) {
        List<Answer> answer = answerService.getAnswersByQuestionId(questionId);
        ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "Answer found successfully", answer);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public  ResponseEntity<ApiResponse<Object>> createAnswer(@RequestBody Answer answer) {
        try {
            UUID userId, questionId;
            try {
                userId = UUID.fromString(answer.getUserId().toString());
                questionId = UUID.fromString(answer.getQuestionId().toString());
            } catch (IllegalArgumentException e) {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "Invalid UUID format.");
                return ResponseEntity.ok(response);
            }

            boolean questionExists = questionRepository.existsById(questionId);
            if (!questionExists) {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "Error: The provided question does not exist");
                return ResponseEntity.ok(response);
            }

            boolean userExists = userRepository.existsById(userId);
            if (!userExists) {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "Error: The provided userId does not exist");
                return ResponseEntity.ok(response);

            }

            ApiResponse<Object> response = answerService.createAnswer(answer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "An unexpected error occurred:");
            return ResponseEntity.ok(response);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable UUID id, @RequestBody Answer updatedAnswer) {
        return ResponseEntity.ok(answerService.updateAnswer(id, updatedAnswer));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<ApiResponse<Object>> deleteAnswer(@PathVariable UUID answerId) {
        Optional<Answer> deletedAnswer = answerService.deleteAnswer(answerId);
        try {
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Question deleted successfully", deletedAnswer);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "Question not found: " + answerId);
            return ResponseEntity.ok(response);
        }

    }
}
