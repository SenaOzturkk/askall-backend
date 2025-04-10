package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.UserActivity;
import com.askall.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<Object>> getActivitiesByUserId(@PathVariable UUID userId) {
        try {
            List<UserActivity> activities = userActivityService.getActivitiesByUserId(userId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcı aktiviteleri başarıyla getirildi", activities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Aktiviteler alınırken bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcı ID'sine ve aksiyona göre aktiviteleri listele
    @GetMapping("/user/{userId}/action/{action}")
    public ResponseEntity<ApiResponse<Object>> getActivitiesByUserIdAndAction(@PathVariable UUID userId, @PathVariable UserActivity.Action action) {
        try {
            List<UserActivity> activities = userActivityService.getActivitiesByUserIdAndAction(userId, action);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcı aksiyon aktiviteleri başarıyla getirildi", activities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Aktivite filtreleme sırasında hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Entity ID'sine göre aktiviteleri listele
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<ApiResponse<Object>> getActivitiesByEntityId(@PathVariable UUID entityId) {
        try {
            List<UserActivity> activities = userActivityService.getActivitiesByEntityId(entityId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Entity'ye ait aktiviteler başarıyla getirildi", activities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Entity aktiviteleri alınırken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveUserActivity(@RequestBody UserActivity userActivity) {
        try {
            // Validate action
            if (userActivity.getAction() == null) {
                return ResponseEntity.ok(ApiResponse.error(HttpStatus.BAD_REQUEST, "Action is required"));
            }

            // Ensure that the action is valid (Enum validation)
            try {
                UserActivity.Action.valueOf(userActivity.getAction().name());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.ok(ApiResponse.error(HttpStatus.BAD_REQUEST, "Invalid action value"));
            }

            UserActivity savedActivity = userActivityService.saveUserActivity(userActivity);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.CREATED, "Aktivite başarıyla kaydedildi", savedActivity);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Aktivite kaydı sırasında bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

}
