package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Notification;
import com.askall.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createNotification(@RequestBody Notification notificationRequest) {
        try {
            Notification notification = notificationService.createNotification(
                    notificationRequest.getUserId(),
                    notificationRequest.getSenderId(),
                    notificationRequest.getNotificationText(),
                    notificationRequest.getEntityId(),
                    notificationRequest.getEntityType()
            );
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.CREATED, "Bildirim başarıyla oluşturuldu", notification));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Bildirim oluşturulurken hata oluştu"));
        }
    }

    // Kullanıcının tüm bildirimlerini listeleme
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getNotifications(@PathVariable UUID userId) {
        try {
            List<Notification> notifications = notificationService.getNotifications(userId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Bildirimler başarıyla getirildi", notifications));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Bildirimler alınırken hata oluştu"));
        }
    }

    // Kullanıcının belirli bir bildirimini okuma
    @PostMapping("/{userId}/mark-read/{notificationId}")
    public ResponseEntity<ApiResponse<Object>> markNotificationAsRead(@PathVariable UUID userId,
                                                                      @PathVariable UUID notificationId) {
        try {
            Notification notification = notificationService.markNotificationAsRead(userId, notificationId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Bildirim okundu olarak işaretlendi", notification));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Bildirim işaretleme sırasında hata oluştu"));
        }
    }

    // Kullanıcının okunmamış bildirimlerini listeleme
    @GetMapping("/{userId}/unread")
    public ResponseEntity<ApiResponse<Object>> getUnreadNotifications(@PathVariable UUID userId) {
        try {
            List<Notification> unread = notificationService.getUnreadNotifications(userId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Okunmamış bildirimler getirildi", unread));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Okunmamış bildirimler getirilirken hata oluştu"));
        }
    }
}
