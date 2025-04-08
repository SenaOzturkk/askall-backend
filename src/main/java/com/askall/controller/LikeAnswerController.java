package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Follow;
import com.askall.modal.LikeAnswer;
import com.askall.service.LikeAnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
public class LikeAnswerController {

    private final LikeAnswerService likeAnswerService;

    public LikeAnswerController(LikeAnswerService likeAnswerService) {
        this.likeAnswerService = likeAnswerService;
    }

    @PostMapping("/{userId}/{answerId}")
    public ResponseEntity<ApiResponse<Object>> likeAnswer(@PathVariable UUID userId, @PathVariable UUID answerId) {
        try {
            Optional<LikeAnswer> likeAnswer = Optional.ofNullable(likeAnswerService.likeAnswer(userId, answerId));
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Like answer successfully", likeAnswer.get());
            return ResponseEntity.ok(response);

        } catch (IllegalStateException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("/{userId}/{answerId}")
    public ResponseEntity<ApiResponse<Object>> unlikeAnswer(@PathVariable UUID userId, @PathVariable UUID answerId) {
        try {
            Optional<LikeAnswer> likeAnswer = likeAnswerService.unlikeAnswer(userId, answerId);
            if (likeAnswer.isPresent()) {
                ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "likeAnswer successfully", likeAnswer.get());
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.NOT_FOUND, "likeAnswer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (RuntimeException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Belirli bir cevabı beğenenleri listeleme
    @GetMapping("/answer/{answerId}")
    public ResponseEntity<ApiResponse<Object>>  getLikesByAnswerId(@PathVariable UUID answerId) {
        Optional<LikeAnswer> likes = likeAnswerService.getLikesByAnswerId(answerId);

        if (likes.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "likes successfully", likes.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "likes not found:", null);
            return ResponseEntity.ok(response);
        }
    }

    // Belirli bir kullanıcının beğendiği cevapları listeleme
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Object>>  getLikesByUserId(@PathVariable UUID userId) {
        Optional<LikeAnswer> likes = likeAnswerService.getLikesByUserId(userId);

        if (likes.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "likes successfully", likes.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "likes not found:", null);
            return ResponseEntity.ok(response);
        }

    }
}
