package com.askall.service;

import com.askall.modal.AskCredit;
import com.askall.repository.AskCreditRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AskCreditService {

    private final AskCreditRepository askCreditRepository;

    public AskCreditService(AskCreditRepository askCreditRepository) {
        this.askCreditRepository = askCreditRepository;
    }

    public AskCredit getCreditsByUserId(UUID userId) {
        return askCreditRepository.findByUserId(userId)
                .orElseGet(() -> new AskCredit(userId, 0)); // Kullanıcı bulunamazsa 0 kredi ile yeni bir nesne döndür.
    }

    // Create: Kullanıcıya ait kredi kaydını oluşturur.
    public AskCredit createCredit(UUID userId, int amount) {
        Optional<AskCredit> existingCredit = askCreditRepository.findByUserId(userId);
        if (existingCredit.isPresent()) {
            throw new DataIntegrityViolationException("This credit record already exists.");
        }

        AskCredit askCredit = new AskCredit(userId, amount);
        return askCreditRepository.save(askCredit);
    }


    // Update: Var olan bir kredi kaydını günceller.
    public AskCredit updateCredits(UUID userId, int amount) {
        // Kullanıcıya ait kredi kaydını bulur
        AskCredit askCredit = askCreditRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kredi kaydı bulunamadı"));

        // Kredi miktarını günceller
        askCredit.setCredits(askCredit.getCredits() + amount);
        return askCreditRepository.save(askCredit);  // Güncellenmiş krediyi kaydeder
    }

}
