package com.askall.service;

import com.askall.modal.Message;
import com.askall.modal.User;
import com.askall.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public Message markAsRead(UUID messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);

        if (optionalMessage.isEmpty()) {
            return null;
        }
        Message message = optionalMessage.get();
        message.setIsRead(true);
        return messageRepository.save(message);
    }


    @Transactional
    public Optional<Message> deleteMessage(UUID messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);

        optionalMessage.ifPresent(message -> {
            message.setIsDeleted(true);
            messageRepository.save(message);
        });

        return optionalMessage;
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
