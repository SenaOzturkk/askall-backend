package com.askall.repository;

import com.askall.modal.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TravelRepository extends JpaRepository<Travel, UUID> {

    // Kullanıcıya ait tüm seyahat kayıtlarını getir
    List<Travel> findByUserId(UUID userId);

    // Seyahat durumuna göre filtreleme yap
    List<Travel> findByStatus(Travel.Status status);

    // Seyahat tarihine göre sıralı şekilde getir
    List<Travel> findAllByOrderByCreatedAtDesc();
}
