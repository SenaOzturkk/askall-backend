package com.askall.service;

import com.askall.modal.Conversation;
import com.askall.repository.ConversationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Conversation createConversation(UUID user1Id, UUID user2Id) {
        Optional<Conversation> existingConversation = conversationRepository
                .findByUser1IdAndUser2Id(user1Id, user2Id);

        if (!existingConversation.isPresent()) {
            // Check for reversed user1Id and user2Id
            existingConversation = conversationRepository.findByUser1IdAndUser2Id(user2Id, user1Id);
        }

        if (existingConversation.isPresent()) {
            return existingConversation.get();
        }

        Conversation conversation = new Conversation();
        conversation.setUser1Id(user1Id);
        conversation.setUser2Id(user2Id);
        return conversationRepository.save(conversation);
    }


    public List<Conversation> getUserConversations(UUID userId) {
        return conversationRepository.findByUser1IdOrUser2Id(userId, userId);
    }
}
