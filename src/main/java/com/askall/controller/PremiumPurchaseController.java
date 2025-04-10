package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.PremiumPurchase;
import com.askall.service.PremiumPurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/premium-purchases")
public class PremiumPurchaseController {

    private final PremiumPurchaseService premiumPurchaseService;

    public PremiumPurchaseController(PremiumPurchaseService premiumPurchaseService) {
        this.premiumPurchaseService = premiumPurchaseService;
    }

    // Yeni premium satın alımı oluştur
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createPremiumPurchase(@RequestBody PremiumPurchase premiumPurchaseRequest) {
        try {
            PremiumPurchase premiumPurchase = premiumPurchaseService.createPremiumPurchase(
                    premiumPurchaseRequest.getUserId(),
                    premiumPurchaseRequest.getExpirationDate(),
                    premiumPurchaseRequest.getAmount(),
                    premiumPurchaseRequest.getPaymentMethod(),
                    premiumPurchaseRequest.getPurchaseType()
            );
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.CREATED, "Premium satın alma başarılı", premiumPurchase);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Satın alma sırasında bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }


    // Kullanıcıya ait tüm premium satın alımları listele
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getPremiumPurchases(@PathVariable UUID userId) {
        try {
            List<PremiumPurchase> purchases = premiumPurchaseService.getPremiumPurchases(userId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcının satın alımları başarıyla getirildi", purchases);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Satın alımlar getirilirken bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcının belirli bir satın alımını getir
    @GetMapping("/{userId}/{purchaseId}")
    public ResponseEntity<ApiResponse<Object>> getPremiumPurchase(@PathVariable UUID userId,
                                                                  @PathVariable UUID purchaseId) {
        try {
            PremiumPurchase purchase = premiumPurchaseService.getPremiumPurchase(userId, purchaseId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Satın alma başarıyla getirildi", purchase);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Satın alma getirilirken bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcının süresi geçmiş satın alımlarını getir
    @GetMapping("/{userId}/expired")
    public ResponseEntity<ApiResponse<Object>> getExpiredPremiumPurchases(@PathVariable UUID userId) {
        try {
            List<PremiumPurchase> expired = premiumPurchaseService.getExpiredPremiumPurchases(userId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Süresi geçmiş satın alımlar listelendi", expired);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Süresi geçmiş satın alımlar getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }
}
