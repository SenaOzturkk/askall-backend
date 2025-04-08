package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Answer;
import com.askall.modal.Follow;
import com.askall.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<ApiResponse<Object>> getFollowers(@PathVariable UUID userId) {
        Optional<Follow> follow = followService.getFollowers(userId);

        if (follow.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "follow successfully", follow.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "follow not found:", null);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/following/{userId}")
    public  ResponseEntity<ApiResponse<Object>> getFollowing(@PathVariable UUID userId) {
        Optional<Follow> follow = followService.getFollowing(userId);

        if (follow.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "following successfully", follow.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "following not found:", null);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/follow")
    public ResponseEntity<ApiResponse<Object>> followUser(@RequestParam UUID followerId, @RequestParam UUID followingId) {
        try {
            Optional<Follow> follow = Optional.of(followService.followUser(followerId, followingId));

            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Follow successfully", follow.get());
            return ResponseEntity.ok(response);

        } catch (IllegalStateException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> unfollowUser(@RequestParam UUID followerId, @RequestParam UUID followingId) {
        Optional<Follow>  unfollow =  followService.unfollowUser(followerId, followingId);
        try {
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "unfollow successfully", unfollow);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "unfollow not found: " + unfollow);
            return ResponseEntity.ok(response);
        }
    }
}
