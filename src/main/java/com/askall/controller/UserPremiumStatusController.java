package com.askall.controller;

import com.askall.modal.UserPremiumStatus;
import com.askall.service.UserPremiumStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-premium-status")
public class UserPremiumStatusController {

    @Autowired
    private UserPremiumStatusService userPremiumStatusService;

    // Kullanıcı ID'sine göre premium durumu al
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserPremiumStatus> getUserPremiumStatus(@PathVariable UUID userId) {
        Optional<UserPremiumStatus> status = userPremiumStatusService.getUserPremiumStatus(userId);
        if (status.isPresent()) {
            return ResponseEntity.ok(status.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Yeni bir premium durumu kaydet
    @PostMapping("/save")
    public ResponseEntity<UserPremiumStatus> saveUserPremiumStatus(@RequestBody UserPremiumStatus userPremiumStatus) {
        UserPremiumStatus savedStatus = userPremiumStatusService.saveUserPremiumStatus(userPremiumStatus);
        return ResponseEntity.ok(savedStatus);
    }

    // Kullanıcı ID'sine göre premium durumu güncelle
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserPremiumStatus> updateUserPremiumStatus(@PathVariable UUID userId, @RequestBody UserPremiumStatus userPremiumStatus) {
        UserPremiumStatus updatedStatus = userPremiumStatusService.updateUserPremiumStatus(userId, userPremiumStatus);
        if (updatedStatus != null) {
            return ResponseEntity.ok(updatedStatus);
        }
        return ResponseEntity.notFound().build();
    }

    // Kullanıcı premium durumunu sil
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUserPremiumStatus(@PathVariable UUID userId) {
        userPremiumStatusService.deleteUserPremiumStatus(userId);
        return ResponseEntity.noContent().build();
    }
}
