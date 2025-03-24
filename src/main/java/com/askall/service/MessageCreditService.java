package com.askall.service;

import com.askall.modal.MessageCredit;
import com.askall.repository.MessageCreditRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageCreditService {

    private final MessageCreditRepository messageCreditRepository;

    public MessageCreditService(MessageCreditRepository messageCreditRepository) {
        this.messageCreditRepository = messageCreditRepository;
    }

    // Kullanıcıya kredi ekleme
    public MessageCredit addCredits(UUID userId, Integer creditsToAdd) {
        MessageCredit messageCredit = messageCreditRepository.findByUserId(userId);
        if (messageCredit != null) {
            messageCredit.setCredits(messageCredit.getCredits() + creditsToAdd);
        } else {
            messageCredit = new MessageCredit();
            messageCredit.setUserId(userId);
            messageCredit.setCredits(creditsToAdd);
        }
        return messageCreditRepository.save(messageCredit);
    }

    // Kullanıcıya kredi silme
    public MessageCredit deductCredits(UUID userId, Integer creditsToDeduct) {
        MessageCredit messageCredit = messageCreditRepository.findByUserId(userId);
        if (messageCredit != null && messageCredit.getCredits() >= creditsToDeduct) {
            messageCredit.setCredits(messageCredit.getCredits() - creditsToDeduct);
            return messageCreditRepository.save(messageCredit);
        } else {
            throw new IllegalArgumentException("Not enough credits or user not found");
        }
    }

    // Kullanıcıya ait kredi bilgilerini getirme
    public MessageCredit getCredits(UUID userId) {
        return messageCreditRepository.findByUserId(userId);
    }
}
