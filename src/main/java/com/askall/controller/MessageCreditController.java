package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.MessageCredit;
import com.askall.service.MessageCreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/message-credits")
public class MessageCreditController {

    private final MessageCreditService messageCreditService;

    public MessageCreditController(MessageCreditService messageCreditService) {
        this.messageCreditService = messageCreditService;
    }

    // Kullanıcıya kredi ekleme
    @PostMapping("/{userId}/add/{creditsToAdd}")
    public ResponseEntity<ApiResponse<Object>> addCredits(@PathVariable UUID userId,
                                                          @PathVariable Integer creditsToAdd) {
        try {
            MessageCredit messageCredit = messageCreditService.addCredits(userId, creditsToAdd);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Kredi başarıyla eklendi", messageCredit));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Kredi ekleme sırasında hata oluştu"));
        }
    }

    // Kullanıcıdan kredi silme
    @PostMapping("/{userId}/deduct/{creditsToDeduct}")
    public ResponseEntity<ApiResponse<Object>> deductCredits(@PathVariable UUID userId,
                                                             @PathVariable Integer creditsToDeduct) {
        try {
            MessageCredit messageCredit = messageCreditService.deductCredits(userId, creditsToDeduct);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Kredi başarıyla düşüldü", messageCredit));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Kredi düşme sırasında hata oluştu"));
        }
    }

    // Kullanıcıya ait kredi bilgilerini getirme
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getCredits(@PathVariable UUID userId) {
        try {
            MessageCredit messageCredit = messageCreditService.getCredits(userId);
            if (messageCredit != null) {
                return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Kredi bilgileri getirildi", messageCredit));
            } else {
                return ResponseEntity.ok(ApiResponse.error(HttpStatus.NOT_FOUND, "Kullanıcıya ait kredi bilgisi bulunamadı"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Kredi bilgisi alınırken hata oluştu"));
        }
    }
}
