package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Message;
import com.askall.modal.User;
import com.askall.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Mesaj gönderme
    @PostMapping("/{conversationId}/{senderId}")
    public ResponseEntity<ApiResponse<Object>> sendMessage(@PathVariable UUID conversationId,
                                                           @PathVariable UUID senderId,
                                                           @RequestBody String messageText) {
        try {
            Message message = messageService.sendMessage(conversationId, senderId, messageText);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Mesaj başarıyla gönderildi", message));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Mesaj gönderilirken hata oluştu"));
        }
    }

    // Mesaj okuma (isRead = true)
    @PatchMapping("/{messageId}/read")
    public ResponseEntity<ApiResponse<Object>> markAsRead(@PathVariable UUID messageId) {
        try {
            Message message = messageService.markAsRead(messageId);

            if (message == null) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Mesaj bulunamadı"));
            }

            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Mesaj okundu olarak işaretlendi", message));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Mesaj okuma sırasında hata oluştu"));
        }
    }



    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse<Object>> deleteMessage(@PathVariable UUID messageId) {
        Optional<Message> deletedMessage = messageService.deleteMessage(messageId);

        if (deletedMessage.isPresent()) {
            return ResponseEntity.ok(
                    ApiResponse.success(HttpStatus.OK, "Mesaj başarıyla silindi", deletedMessage.get())
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Mesaj bulunamadı"));
        }
    }



    // Konuşmadaki tüm mesajları listeleme
    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<ApiResponse<Object>> getMessagesByConversationId(@PathVariable UUID conversationId) {
        try {
            List<Message> messages = messageService.getMessagesByConversationId(conversationId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Mesajlar listelendi", messages));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Mesajlar getirilirken hata oluştu"));
        }
    }

    // Okunmuş mesajlar
    @GetMapping("/conversation/{conversationId}/read/{senderId}")
    public ResponseEntity<ApiResponse<Object>> getReadMessages(@PathVariable UUID conversationId, @PathVariable UUID senderId) {
        try {
            List<Message> messages = messageService.getReadMessages(conversationId, senderId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Okunan mesajlar listelendi", messages));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Okunan mesajlar getirilirken hata oluştu"));
        }
    }

    // Okunmamış mesajlar
    @GetMapping("/conversation/{conversationId}/unread/{senderId}")
    public ResponseEntity<ApiResponse<Object>> getUnreadMessages(@PathVariable UUID conversationId, @PathVariable UUID senderId) {
        try {
            List<Message> messages = messageService.getUnreadMessages(conversationId, senderId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Okunmamış mesajlar listelendi", messages));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Okunmamış mesajlar getirilirken hata oluştu"));
        }
    }

    // Silinen mesajlar
    @GetMapping("/conversation/{conversationId}/deleted")
    public ResponseEntity<ApiResponse<Object>> getDeletedMessages(@PathVariable UUID conversationId) {
        try {
            List<Message> messages = messageService.getDeletedMessages(conversationId);
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Silinen mesajlar listelendi", messages));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Silinen mesajlar getirilirken hata oluştu"));
        }
    }
}
