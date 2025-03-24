package com.askall.service;

import com.askall.modal.Message;
import com.askall.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Mesaj gönderme
    public Message sendMessage(UUID conversationId, UUID senderId, String messageText) {
        Message message = new Message();
        message.setConversationId(conversationId);
        message.setSenderId(senderId);
        message.setMessageText(messageText);
        return messageRepository.save(message);
    }

    // Mesaj okuma
    public Message markAsRead(UUID messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        message.setIsRead(true);
        return messageRepository.save(message);
    }

    // Mesajı silme
    public void deleteMessage(UUID messageId) {
        messageRepository.deleteByMessageId(messageId);
    }

    // Belirli bir konuşmanın mesajlarını listeleme
    public List<Message> getMessagesByConversationId(UUID conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    // Belirli bir konuşmadaki okunan mesajları listeleme
    public List<Message> getReadMessages(UUID conversationId, UUID senderId) {
        return messageRepository.findByConversationIdAndSenderIdAndIsRead(conversationId, senderId, true);
    }

    // Belirli bir konuşmadaki okunmamış mesajları listeleme
    public List<Message> getUnreadMessages(UUID conversationId, UUID senderId) {
        return messageRepository.findByConversationIdAndSenderIdAndIsRead(conversationId, senderId, false);
    }

    // Belirli bir konuşmadaki silinen mesajları listeleme
    public List<Message> getDeletedMessages(UUID conversationId) {
        return messageRepository.findByConversationIdAndIsDeleted(conversationId, true);
    }
}
