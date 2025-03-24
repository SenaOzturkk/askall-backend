package com.askall.repository;

import com.askall.modal.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, UUID> {
    Optional<BlockedUser> findByBlockerIdAndBlockedId(UUID blockerId, UUID blockedId);
    List<BlockedUser> findByBlockerId(UUID blockerId);
    boolean existsByBlockerIdAndBlockedId(UUID blockerId, UUID blockedId);
    void deleteByBlockerIdAndBlockedId(UUID blockerId, UUID blockedId);
}
