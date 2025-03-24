package com.askall.repository;

import com.askall.modal.PremiumPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface PremiumPurchaseRepository extends JpaRepository<PremiumPurchase, UUID> {

    // Kullanıcıya ait tüm premium satın alımlarını getir
    List<PremiumPurchase> findByUserId(UUID userId);

    // Kullanıcının belirli bir premium satın alımını getir
    PremiumPurchase findByUserIdAndPurchaseId(UUID userId, UUID purchaseId);

    // Kullanıcının geçerlilik süresi bitmiş premium satın alımlarını getir
    List<PremiumPurchase> findByUserIdAndExpirationDateBefore(UUID userId, Instant now);
}
