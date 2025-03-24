package com.askall.controller;

import com.askall.modal.Message;
import com.askall.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Mesaj gönderme
    @PostMapping("/{conversationId}/{senderId}")
    public ResponseEntity<Message> sendMessage(@PathVariable UUID conversationId, @PathVariable UUID senderId, @RequestBody String messageText) {
        Message message = messageService.sendMessage(conversationId, senderId, messageText);
        return ResponseEntity.ok(message);
    }

    // Mesaj okuma (isRead = true)
    @PatchMapping("/{messageId}/read")
    public ResponseEntity<Message> markAsRead(@PathVariable UUID messageId) {
        Message message = messageService.markAsRead(messageId);
        return ResponseEntity.ok(message);
    }

    // Mesaj silme
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    // Belirli bir konuşmadaki tüm mesajları listeleme
    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<Message>> getMessagesByConversationId(@PathVariable UUID conversationId) {
        List<Message> messages = messageService.getMessagesByConversationId(conversationId);
        return ResponseEntity.ok(messages);
    }

    // Belirli bir konuşmadaki okunan mesajları listeleme
    @GetMapping("/conversation/{conversationId}/read/{senderId}")
    public ResponseEntity<List<Message>> getReadMessages(@PathVariable UUID conversationId, @PathVariable UUID senderId) {
        List<Message> messages = messageService.getReadMessages(conversationId, senderId);
        return ResponseEntity.ok(messages);
    }

    // Belirli bir konuşmadaki okunmamış mesajları listeleme
    @GetMapping("/conversation/{conversationId}/unread/{senderId}")
    public ResponseEntity<List<Message>> getUnreadMessages(@PathVariable UUID conversationId, @PathVariable UUID senderId) {
        List<Message> messages = messageService.getUnreadMessages(conversationId, senderId);
        return ResponseEntity.ok(messages);
    }

    // Belirli bir konuşmadaki silinen mesajları listeleme
    @GetMapping("/conversation/{conversationId}/deleted")
    public ResponseEntity<List<Message>> getDeletedMessages(@PathVariable UUID conversationId) {
        List<Message> messages = messageService.getDeletedMessages(conversationId);
        return ResponseEntity.ok(messages);
    }
}
