package com.askall.service;

import com.askall.modal.AskCredit;
import com.askall.repository.AskCreditRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AskCreditService {

    private final AskCreditRepository askCreditRepository;

    public AskCreditService(AskCreditRepository askCreditRepository) {
        this.askCreditRepository = askCreditRepository;
    }
/*
    public AskCredit getCreditsByUserId(UUID userId) {
        return askCreditRepository.findByUserId(userId)
                .orElseGet(() -> new AskCredit(userId, 0)); // Kullanıcı bulunamazsa 0 kredi ile yeni bir nesne döndür.
    }*/
/*
    public AskCredit updateCredits(UUID userId, int amount) {
        AskCredit askCredit = askCreditRepository.findByUserId(userId)
                .orElseGet(() -> new AskCredit(userId, 0));

        askCredit.setCredits(askCredit.getCredits() + amount);
        return askCreditRepository.save(askCredit);
    }

    */
}
