package com.askall.controller;

import com.askall.modal.Conversation;
import com.askall.service.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.askall.dto.ApiResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> startConversation(@RequestBody Conversation conversationRequest) {
        try {
            Conversation conversation = conversationService.createConversation(conversationRequest.getUser1Id(), conversationRequest.getUser2Id());
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.CREATED, "Conversation started successfully", conversation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Error starting conversation"));
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Object>> getUserConversations(@PathVariable UUID userId) {
        List<Conversation> conversations = conversationService.getUserConversations(userId);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Conversations fetched successfully", conversations));
    }

}
