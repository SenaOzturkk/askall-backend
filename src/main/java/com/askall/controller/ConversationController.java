package com.askall.controller;

import com.askall.modal.Conversation;
import com.askall.service.ConversationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/start")
    public ResponseEntity<Conversation> startConversation(@RequestParam UUID user1Id, @RequestParam UUID user2Id) {
        return ResponseEntity.ok(conversationService.createConversation(user1Id, user2Id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Conversation>> getUserConversations(@PathVariable UUID userId) {
        return ResponseEntity.ok(conversationService.getUserConversations(userId));
    }
}
