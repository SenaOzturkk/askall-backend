package com.askall.repository;

import com.askall.modal.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, UUID> {

    // Kullanıcı ID'sine göre yapılan tüm aktiviteleri bulma
    List<UserActivity> findByUserId(UUID userId);

    // Kullanıcı ID'si ve aksiyona göre aktiviteleri bulma
    List<UserActivity> findByUserIdAndAction(UUID userId, UserActivity.Action action);

    // Entity ID'sine göre aktiviteleri bulma
    List<UserActivity> findByEntityId(UUID entityId);
}
