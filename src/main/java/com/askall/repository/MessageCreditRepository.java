package com.askall.repository;

import com.askall.modal.MessageCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageCreditRepository extends JpaRepository<MessageCredit, UUID> {

    // Kullanıcı ID'si ile kredi bilgisi getir
    MessageCredit findByUserId(UUID userId);

    // Kredi miktarını güncelleme
    MessageCredit save(MessageCredit messageCredit);
}
