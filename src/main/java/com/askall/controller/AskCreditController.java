package com.askall.controller;

import com.askall.modal.AskCredit;
import com.askall.service.AskCreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/credits")
public class AskCreditController {

    private final AskCreditService askCreditService;

    public AskCreditController(AskCreditService askCreditService) {
        this.askCreditService = askCreditService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AskCredit> getUserCredits(@PathVariable UUID userId) {
        AskCredit askCredit = askCreditService.getCreditsByUserId(userId);
        return ResponseEntity.ok(askCredit);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<AskCredit> addCredits(@PathVariable UUID userId, @RequestParam int amount) {
        AskCredit updatedCredit = askCreditService.updateCredits(userId, amount);
        return ResponseEntity.ok(updatedCredit);
    }
}
