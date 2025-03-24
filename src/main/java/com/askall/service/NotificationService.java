package com.askall.service;

import com.askall.modal.Notification;
import com.askall.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Kullanıcıya yeni bildirim ekleme
    public Notification createNotification(UUID userId, UUID senderId, String notificationText, UUID entityId, Notification.EntityType entityType) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setSenderId(senderId);
        notification.setNotificationText(notificationText);
        notification.setEntityId(entityId);
        notification.setEntityType(entityType);
        notification.setIsRead(false);
        return notificationRepository.save(notification);
    }

    // Kullanıcının tüm bildirimlerini listeleme
    public List<Notification> getNotifications(UUID userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Kullanıcının belirli bir bildirimini okuma (isRead = true)
    public Notification markNotificationAsRead(UUID userId, UUID notificationId) {
        Notification notification = notificationRepository.findByUserIdAndNotificationId(userId, notificationId);
        if (notification != null) {
            notification.setIsRead(true);
            return notificationRepository.save(notification);
        } else {
            throw new IllegalArgumentException("Notification not found");
        }
    }

    // Kullanıcının okunmamış bildirimlerini listeleme
    public List<Notification> getUnreadNotifications(UUID userId) {
        return notificationRepository.findByUserIdAndIsRead(userId, false);
    }
}
