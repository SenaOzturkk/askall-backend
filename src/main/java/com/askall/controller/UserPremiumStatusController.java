package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.User;
import com.askall.modal.UserPremiumStatus;
import com.askall.service.UserPremiumStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<Object>> getUserPremiumStatus(@PathVariable UUID userId) {
        try {
            Optional<UserPremiumStatus> status = userPremiumStatusService.getUserPremiumStatus(userId);
            if (status.isPresent()) {
                ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcının premium durumu başarıyla getirildi", status.get());
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.NOT_FOUND, "Kullanıcının premium durumu bulunamadı");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Premium durumu getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Yeni bir premium durumu kaydet
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveUserPremiumStatus(@RequestBody UserPremiumStatus userPremiumStatus) {
        try {
            UserPremiumStatus savedStatus = userPremiumStatusService.saveUserPremiumStatus(userPremiumStatus);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.CREATED, "Premium durumu başarıyla kaydedildi", savedStatus);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Premium durumu kaydedilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcı ID'sine göre premium durumu güncelle
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> updateUserPremiumStatus(@PathVariable UUID userId, @RequestBody UserPremiumStatus userPremiumStatus) {
        try {
            UserPremiumStatus updatedStatus = userPremiumStatusService.updateUserPremiumStatus(userId, userPremiumStatus);
            if (updatedStatus != null) {
                ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Premium durumu başarıyla güncellendi", updatedStatus);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.NOT_FOUND, "Güncellenecek kullanıcı premium durumu bulunamadı");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Premium durumu güncellenirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcı premium durumunu sil
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUserPremiumStatus(@PathVariable UUID userId) {
        try {
            Optional<UserPremiumStatus> deletedUserPremium = userPremiumStatusService.deleteUserPremiumStatus(userId);
            if (deletedUserPremium.isPresent()) {
                ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Premium durumu başarıyla silindi", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Object> response = ApiResponse.error(HttpStatus.NOT_FOUND, "Premium durumu silinirken hata oluştu " + userId);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Premium durumu silinirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }
}
