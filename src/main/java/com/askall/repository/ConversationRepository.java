package com.askall.repository;

import com.askall.modal.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
    Optional<Conversation> findByUser1IdAndUser2Id(UUID user1Id, UUID user2Id);
    List<Conversation> findByUser1IdOrUser2Id(UUID userId1, UUID userId2);
}
