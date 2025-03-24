package com.askall.controller;

import com.askall.modal.MessageCredit;
import com.askall.service.MessageCreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/message-credits")
public class MessageCreditController {

    private final MessageCreditService messageCreditService;

    public MessageCreditController(MessageCreditService messageCreditService) {
        this.messageCreditService = messageCreditService;
    }

    // Kullanıcıya kredi ekleme
    @PostMapping("/{userId}/add/{creditsToAdd}")
    public ResponseEntity<MessageCredit> addCredits(@PathVariable UUID userId, @PathVariable Integer creditsToAdd) {
        MessageCredit messageCredit = messageCreditService.addCredits(userId, creditsToAdd);
        return ResponseEntity.ok(messageCredit);
    }

    // Kullanıcıdan kredi silme
    @PostMapping("/{userId}/deduct/{creditsToDeduct}")
    public ResponseEntity<MessageCredit> deductCredits(@PathVariable UUID userId, @PathVariable Integer creditsToDeduct) {
        MessageCredit messageCredit = messageCreditService.deductCredits(userId, creditsToDeduct);
        return ResponseEntity.ok(messageCredit);
    }

    // Kullanıcıya ait kredi bilgilerini getirme
    @GetMapping("/{userId}")
    public ResponseEntity<MessageCredit> getCredits(@PathVariable UUID userId) {
        MessageCredit messageCredit = messageCreditService.getCredits(userId);
        if (messageCredit != null) {
            return ResponseEntity.ok(messageCredit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
