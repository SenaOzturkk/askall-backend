package com.askall.controller;

import com.askall.modal.Notification;
import com.askall.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Kullanıcıya bildirim ekleme
    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestParam UUID userId,
                                                           @RequestParam UUID senderId,
                                                           @RequestParam String notificationText,
                                                           @RequestParam UUID entityId,
                                                           @RequestParam Notification.EntityType entityType) {
        Notification notification = notificationService.createNotification(userId, senderId, notificationText, entityId, entityType);
        return ResponseEntity.ok(notification);
    }

    // Kullanıcının tüm bildirimlerini listeleme
    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID userId) {
        List<Notification> notifications = notificationService.getNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // Kullanıcının belirli bir bildirimini okuma
    @PostMapping("/{userId}/mark-read/{notificationId}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable UUID userId, @PathVariable UUID notificationId) {
        Notification notification = notificationService.markNotificationAsRead(userId, notificationId);
        return ResponseEntity.ok(notification);
    }

    // Kullanıcının okunmamış bildirimlerini listeleme
    @GetMapping("/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable UUID userId) {
        List<Notification> unreadNotifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(unreadNotifications);
    }
}
