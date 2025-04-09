package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.LikeQuestion;
import com.askall.service.LikeQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes/questions")
public class LikeQuestionController {

    private final LikeQuestionService likeQuestionService;

    public LikeQuestionController(LikeQuestionService likeQuestionService) {
        this.likeQuestionService = likeQuestionService;
    }

    // Soruyu beğenme
    @PostMapping("/{userId}/{questionId}")
    public ResponseEntity<ApiResponse<Object>> likeQuestion(@PathVariable UUID userId,
                                                            @PathVariable UUID questionId) {
        try {
            LikeQuestion likeQuestion = likeQuestionService.likeQuestion(userId, questionId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Soru başarıyla beğenildi", likeQuestion));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Soruyu beğenirken hata oluştu"));
        }
    }

    // Beğeniyi kaldırma
    @DeleteMapping("/{userId}/{questionId}")
    public ResponseEntity<ApiResponse<Object>> unlikeQuestion(@PathVariable UUID userId,
                                                              @PathVariable UUID questionId) {
        try {
            likeQuestionService.unlikeQuestion(userId, questionId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.NO_CONTENT, "Beğeni başarıyla kaldırıldı", null));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Beğeni kaldırılırken hata oluştu"));
        }
    }

    // Belirli bir soruyu beğenenleri listeleme
    @GetMapping("/question/{questionId}")
    public ResponseEntity<ApiResponse<Object>> getLikesByQuestionId(@PathVariable UUID questionId) {
        try {
            List<LikeQuestion> likes = likeQuestionService.getLikesByQuestionId(questionId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Soruyu beğenen kullanıcılar listelendi", likes));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Beğeni bilgileri getirilirken hata oluştu"));
        }
    }

    // Belirli bir kullanıcının beğendiği soruları listeleme
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Object>> getLikesByUserId(@PathVariable UUID userId) {
        try {
            List<LikeQuestion> likes = likeQuestionService.getLikesByUserId(userId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Kullanıcının beğendiği sorular listelendi", likes));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Kullanıcı beğenileri getirilirken hata oluştu"));
        }
    }
}
