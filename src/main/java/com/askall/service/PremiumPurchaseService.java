package com.askall.service;

import com.askall.modal.PremiumPurchase;
import com.askall.repository.PremiumPurchaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PremiumPurchaseService {

    private final PremiumPurchaseRepository premiumPurchaseRepository;

    public PremiumPurchaseService(PremiumPurchaseRepository premiumPurchaseRepository) {
        this.premiumPurchaseRepository = premiumPurchaseRepository;
    }

    // Premium satın alımı oluştur
    public PremiumPurchase createPremiumPurchase(UUID userId, Instant expirationDate, BigDecimal amount, String paymentMethod, String purchaseType) {
        PremiumPurchase premiumPurchase = new PremiumPurchase();
        premiumPurchase.setUserId(userId);
        premiumPurchase.setExpirationDate(expirationDate);
        premiumPurchase.setAmount(amount);
        premiumPurchase.setPaymentMethod(paymentMethod);
        premiumPurchase.setPurchaseType(purchaseType);
        return premiumPurchaseRepository.save(premiumPurchase);
    }

    // Kullanıcıya ait tüm premium satın alımlarını getir
    public List<PremiumPurchase> getPremiumPurchases(UUID userId) {
        return premiumPurchaseRepository.findByUserId(userId);
    }

    // Kullanıcının belirli bir premium satın alımını getir
    public PremiumPurchase getPremiumPurchase(UUID userId, UUID purchaseId) {
        return premiumPurchaseRepository.findByUserIdAndPurchaseId(userId, purchaseId);
    }

    // Geçerlilik süresi bitmiş premium satın alımlarını getir
    public List<PremiumPurchase> getExpiredPremiumPurchases(UUID userId) {
        Instant now = Instant.now();
        return premiumPurchaseRepository.findByUserIdAndExpirationDateBefore(userId, now);
    }
}
