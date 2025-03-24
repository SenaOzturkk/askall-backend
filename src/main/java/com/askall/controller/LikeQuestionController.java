package com.askall.controller;

import com.askall.modal.LikeQuestion;
import com.askall.service.LikeQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/likes/questions")
public class LikeQuestionController {

    private final LikeQuestionService likeQuestionService;

    public LikeQuestionController(LikeQuestionService likeQuestionService) {
        this.likeQuestionService = likeQuestionService;
    }

    // Soruyu beğenme
    @PostMapping("/{userId}/{questionId}")
    public ResponseEntity<LikeQuestion> likeQuestion(@PathVariable UUID userId, @PathVariable UUID questionId) {
        LikeQuestion likeQuestion = likeQuestionService.likeQuestion(userId, questionId);
        return ResponseEntity.ok(likeQuestion);
    }

    // Beğeniyi kaldırma
    @DeleteMapping("/{userId}/{questionId}")
    public ResponseEntity<Void> unlikeQuestion(@PathVariable UUID userId, @PathVariable UUID questionId) {
        likeQuestionService.unlikeQuestion(userId, questionId);
        return ResponseEntity.noContent().build();
    }

    // Belirli bir soruyu beğenenleri listeleme
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<LikeQuestion>> getLikesByQuestionId(@PathVariable UUID questionId) {
        List<LikeQuestion> likes = likeQuestionService.getLikesByQuestionId(questionId);
        return ResponseEntity.ok(likes);
    }

    // Belirli bir kullanıcının beğendiği soruları listeleme
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeQuestion>> getLikesByUserId(@PathVariable UUID userId) {
        List<LikeQuestion> likes = likeQuestionService.getLikesByUserId(userId);
        return ResponseEntity.ok(likes);
    }
}
