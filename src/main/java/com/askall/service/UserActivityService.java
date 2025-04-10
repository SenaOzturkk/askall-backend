package com.askall.service;

import com.askall.modal.UserActivity;
import com.askall.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    // Kullanıcı ID'sine göre aktiviteleri al
    public List<UserActivity> getActivitiesByUserId(UUID userId) {
        return userActivityRepository.findByUserId(userId);
    }

    // Kullanıcı ID'sine ve aksiyona göre aktiviteleri al
    public List<UserActivity> getActivitiesByUserIdAndAction(UUID userId, UserActivity.Action action) {
        return userActivityRepository.findByUserIdAndAction(userId, action);
    }

    // Entity ID'sine göre aktiviteleri al
    public List<UserActivity> getActivitiesByEntityId(UUID entityId) {
        return userActivityRepository.findByEntityId(entityId);
    }

    public UserActivity saveUserActivity(UserActivity userActivity) {
        try {
            // Log incoming activity details
            System.out.println("Saving activity for user: " + userActivity.getUserId());

            // Ensure that action is not null
            if (userActivity.getAction() == null) {
                throw new IllegalArgumentException("Action cannot be null");
            }

            // Save the activity
            return userActivityRepository.save(userActivity);
        } catch (Exception e) {
            // Log the error for debugging
            System.out.println("Error occurred while saving user activity: " + e.getMessage());
            throw new RuntimeException("Error saving user activity", e);
        }
    }


}
