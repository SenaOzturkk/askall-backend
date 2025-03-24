package com.askall.controller;

import com.askall.modal.PremiumPurchase;
import com.askall.service.PremiumPurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/premium-purchases")
public class PremiumPurchaseController {

    private final PremiumPurchaseService premiumPurchaseService;

    public PremiumPurchaseController(PremiumPurchaseService premiumPurchaseService) {
        this.premiumPurchaseService = premiumPurchaseService;
    }

    // Yeni premium satın alımı oluştur
    @PostMapping("/create")
    public ResponseEntity<PremiumPurchase> createPremiumPurchase(@RequestParam UUID userId,
                                                                 @RequestParam Instant expirationDate,
                                                                 @RequestParam BigDecimal amount,
                                                                 @RequestParam String paymentMethod,
                                                                 @RequestParam String purchaseType) {
        PremiumPurchase premiumPurchase = premiumPurchaseService.createPremiumPurchase(userId, expirationDate, amount, paymentMethod, purchaseType);
        return ResponseEntity.ok(premiumPurchase);
    }

    // Kullanıcıya ait tüm premium satın alımlarını listele
    @GetMapping("/{userId}")
    public ResponseEntity<List<PremiumPurchase>> getPremiumPurchases(@PathVariable UUID userId) {
        List<PremiumPurchase> premiumPurchases = premiumPurchaseService.getPremiumPurchases(userId);
        return ResponseEntity.ok(premiumPurchases);
    }

    // Kullanıcının belirli bir premium satın alımını getir
    @GetMapping("/{userId}/{purchaseId}")
    public ResponseEntity<PremiumPurchase> getPremiumPurchase(@PathVariable UUID userId, @PathVariable UUID purchaseId) {
        PremiumPurchase premiumPurchase = premiumPurchaseService.getPremiumPurchase(userId, purchaseId);
        return ResponseEntity.ok(premiumPurchase);
    }

    // Geçerlilik süresi bitmiş premium satın alımlarını getir
    @GetMapping("/{userId}/expired")
    public ResponseEntity<List<PremiumPurchase>> getExpiredPremiumPurchases(@PathVariable UUID userId) {
        List<PremiumPurchase> expiredPremiumPurchases = premiumPurchaseService.getExpiredPremiumPurchases(userId);
        return ResponseEntity.ok(expiredPremiumPurchases);
    }
}
