package com.askall.controller;

import com.askall.modal.Question;
import com.askall.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Yeni bir soru oluştur
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestParam UUID userId,
                                                   @RequestParam String questionText,
                                                   @RequestParam String questionImage,
                                                   @RequestParam Double latitude,
                                                   @RequestParam Double longitude,
                                                   @RequestParam Question.Visibility visibility,
                                                   @RequestParam Instant expiresAt,
                                                   @RequestParam Double radius) {
        Question question = questionService.createQuestion(userId, questionText, questionImage, latitude, longitude, visibility, expiresAt, radius);
        return ResponseEntity.ok(question);
    }

    // Kullanıcının tüm sorularını listele
    @GetMapping("/{userId}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable UUID userId) {
        List<Question> questions = questionService.getQuestionsByUser(userId);
        return ResponseEntity.ok(questions);
    }

    // Kullanıcının belirli bir sorusunu getir
    @GetMapping("/{userId}/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable UUID userId, @PathVariable UUID questionId) {
        Question question = questionService.getQuestion(userId, questionId);
        return ResponseEntity.ok(question);
    }

    // Görünürlüğe göre soruları listele
    @GetMapping("/visibility/{visibility}")
    public ResponseEntity<List<Question>> getQuestionsByVisibility(@PathVariable Question.Visibility visibility) {
        List<Question> questions = questionService.getQuestionsByVisibility(visibility);
        return ResponseEntity.ok(questions);
    }

    // Geçerliliği bitmiş soruları getir
    @GetMapping("/expired/{visibility}")
    public ResponseEntity<List<Question>> getExpiredQuestions(@PathVariable Question.Visibility visibility) {
        Instant now = Instant.now();
        List<Question> expiredQuestions = questionService.getExpiredQuestions(now, visibility);
        return ResponseEntity.ok(expiredQuestions);
    }
}
