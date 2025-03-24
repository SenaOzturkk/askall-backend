package com.askall.controller;

import com.askall.modal.LikeAnswer;
import com.askall.service.LikeAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/likes")
public class LikeAnswerController {

    private final LikeAnswerService likeAnswerService;

    public LikeAnswerController(LikeAnswerService likeAnswerService) {
        this.likeAnswerService = likeAnswerService;
    }

    // Cevabı beğenme
    @PostMapping("/{userId}/{answerId}")
    public ResponseEntity<LikeAnswer> likeAnswer(@PathVariable UUID userId, @PathVariable UUID answerId) {
        LikeAnswer likeAnswer = likeAnswerService.likeAnswer(userId, answerId);
        return ResponseEntity.ok(likeAnswer);
    }

    // Beğeniyi kaldırma
    @DeleteMapping("/{userId}/{answerId}")
    public ResponseEntity<Void> unlikeAnswer(@PathVariable UUID userId, @PathVariable UUID answerId) {
        likeAnswerService.unlikeAnswer(userId, answerId);
        return ResponseEntity.noContent().build();
    }

    // Belirli bir cevabı beğenenleri listeleme
    @GetMapping("/answer/{answerId}")
    public ResponseEntity<List<LikeAnswer>> getLikesByAnswerId(@PathVariable UUID answerId) {
        List<LikeAnswer> likes = likeAnswerService.getLikesByAnswerId(answerId);
        return ResponseEntity.ok(likes);
    }

    // Belirli bir kullanıcının beğendiği cevapları listeleme
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeAnswer>> getLikesByUserId(@PathVariable UUID userId) {
        List<LikeAnswer> likes = likeAnswerService.getLikesByUserId(userId);
        return ResponseEntity.ok(likes);
    }
}
