package com.askall.repository;

import com.askall.modal.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // Kullanıcıya ait tüm bildirimleri getir
    List<Notification> findByUserId(UUID userId);

    // Kullanıcının belirli bir bildirimini oku
    Notification findByUserIdAndNotificationId(UUID userId, UUID notificationId);

    // Kullanıcıya ait okunmamış bildirimleri getir
    List<Notification> findByUserIdAndIsRead(UUID userId, Boolean isRead);
}
