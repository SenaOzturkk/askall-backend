package com.askall.repository;

import com.askall.modal.Message;
import com.askall.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    // Belirli bir konuşma için mesajları listeleme
    List<Message> findByConversationId(UUID conversationId);

    // Kullanıcının belirli bir konuşmadaki okunan/okunmamış mesajlarını getirme
    List<Message> findByConversationIdAndSenderIdAndIsRead(UUID conversationId, UUID senderId, Boolean isRead);

    // Kullanıcının belirli bir konuşmadaki silinen mesajları getirme
    List<Message> findByConversationIdAndIsDeleted(UUID conversationId, Boolean isDeleted);

    // Mesajı silme
    Optional<Message> deleteByMessageId(UUID messageId);
}
