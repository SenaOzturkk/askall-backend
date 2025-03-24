package com.askall.controller;

import com.askall.modal.UserActivity;
import com.askall.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-activity")
public class UserActivityController {

    @Autowired
    private UserActivityService userActivityService;

    // Kullanıcı ID'sine göre aktiviteleri listele
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserActivity>> getActivitiesByUserId(@PathVariable UUID userId) {
        List<UserActivity> activities = userActivityService.getActivitiesByUserId(userId);
        return ResponseEntity.ok(activities);
    }

    // Kullanıcı ID'sine ve aksiyona göre aktiviteleri listele
    @GetMapping("/user/{userId}/action/{action}")
    public ResponseEntity<List<UserActivity>> getActivitiesByUserIdAndAction(@PathVariable UUID userId, @PathVariable UserActivity.Action action) {
        List<UserActivity> activities = userActivityService.getActivitiesByUserIdAndAction(userId, action);
        return ResponseEntity.ok(activities);
    }

    // Entity ID'sine göre aktiviteleri listele
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<UserActivity>> getActivitiesByEntityId(@PathVariable UUID entityId) {
        List<UserActivity> activities = userActivityService.getActivitiesByEntityId(entityId);
        return ResponseEntity.ok(activities);
    }

    // Yeni bir aktivite kaydet
    @PostMapping("/save")
    public ResponseEntity<UserActivity> saveUserActivity(@RequestBody UserActivity userActivity) {
        UserActivity savedActivity = userActivityService.saveUserActivity(userActivity);
        return ResponseEntity.ok(savedActivity);
    }
}
