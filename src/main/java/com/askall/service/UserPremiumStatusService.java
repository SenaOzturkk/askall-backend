package com.askall.service;

import com.askall.modal.User;
import com.askall.modal.UserPremiumStatus;
import com.askall.repository.UserPremiumStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserPremiumStatusService {

    @Autowired
    private UserPremiumStatusRepository userPremiumStatusRepository;

    // Kullanıcı ID'sine göre premium durumu al
    public Optional<UserPremiumStatus> getUserPremiumStatus(UUID userId) {
        return userPremiumStatusRepository.findByUserId(userId);
    }

    // Yeni bir premium durumu kaydet
    public UserPremiumStatus saveUserPremiumStatus(UserPremiumStatus userPremiumStatus) {
        return userPremiumStatusRepository.save(userPremiumStatus);
    }

    // Kullanıcı ID'sine göre premium durumu güncelle
    public UserPremiumStatus updateUserPremiumStatus(UUID userId, UserPremiumStatus userPremiumStatus) {
        Optional<UserPremiumStatus> existingStatus = userPremiumStatusRepository.findByUserId(userId);
        if (existingStatus.isPresent()) {
            UserPremiumStatus status = existingStatus.get();
            status.setIsPremium(userPremiumStatus.getIsPremium());
            status.setExpirationDate(userPremiumStatus.getExpirationDate());
            status.setMessageRightsLeft(userPremiumStatus.getMessageRightsLeft());
            status.setQuestionRightsLeft(userPremiumStatus.getQuestionRightsLeft());
            return userPremiumStatusRepository.save(status);
        }
        return null;
    }

    public Optional<UserPremiumStatus> deleteUserPremiumStatus(UUID userId) {
        Optional<UserPremiumStatus> userOpt = userPremiumStatusRepository.findByUserId(userId);
        if (userOpt.isPresent()) {
            UserPremiumStatus user = userOpt.get();
            user.setIsDeleted(true);
            userPremiumStatusRepository.save(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

}
