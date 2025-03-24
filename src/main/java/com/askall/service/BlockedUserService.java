package com.askall.service;

import com.askall.modal.BlockedUser;
import com.askall.repository.BlockedUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlockedUserService {

    private final BlockedUserRepository blockedUserRepository;

    public BlockedUserService(BlockedUserRepository blockedUserRepository) {
        this.blockedUserRepository = blockedUserRepository;
    }

    public BlockedUser blockUser(UUID blockerId, UUID blockedId) {
        if (blockedUserRepository.existsByBlockerIdAndBlockedId(blockerId, blockedId)) {
            throw new RuntimeException("User is already blocked.");
        }

        BlockedUser blockedUser = new BlockedUser(blockerId, blockedId);
        return blockedUserRepository.save(blockedUser);
    }

    public List<BlockedUser> getBlockedUsers(UUID blockerId) {
        return blockedUserRepository.findByBlockerId(blockerId);
    }

    public boolean isUserBlocked(UUID blockerId, UUID blockedId) {
        return blockedUserRepository.existsByBlockerIdAndBlockedId(blockerId, blockedId);
    }

    public void unblockUser(UUID blockerId, UUID blockedId) {
        blockedUserRepository.deleteByBlockerIdAndBlockedId(blockerId, blockedId);
    }
}
