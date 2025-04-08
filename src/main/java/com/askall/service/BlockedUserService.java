package com.askall.service;

import com.askall.dto.ApiResponse;
import com.askall.modal.BlockedUser;
import com.askall.modal.Question;
import com.askall.repository.BlockedUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlockedUserService {

    private final BlockedUserRepository blockedUserRepository;

    public BlockedUserService(BlockedUserRepository blockedUserRepository) {
        this.blockedUserRepository = blockedUserRepository;
    }

    public ApiResponse<Object> blockUser(UUID blockerId, UUID blockedId) {
        if (blockedUserRepository.existsByBlockerIdAndBlockedId(blockerId, blockedId)) {
            throw new RuntimeException("User is already blocked.");
        }

        BlockedUser blockedUser = new BlockedUser(blockerId, blockedId);
        blockedUserRepository.save(blockedUser);

        return ApiResponse.success(HttpStatus.OK, "Block successfully", blockedUser);
    }

    public ApiResponse<Object> getBlockedUsers(UUID blockerId) {
        List<BlockedUser> blockedUser =  blockedUserRepository.findByBlockerId(blockerId);
        return ApiResponse.success(HttpStatus.OK, "Block successfully", blockedUser);
    }

    public Optional<BlockedUser> unblockUser(UUID blockerId, UUID blockedId) {
        Optional<BlockedUser> blockedUser = blockedUserRepository.findByBlockerIdAndBlockedId(blockerId, blockedId);

        blockedUser.ifPresent(user -> {
            user.setDeleted(true);
            user.setUnblockedAt(Instant.now()); // UTC zaman damgası
            blockedUserRepository.save(user);
        });

        return blockedUser;
    }



}
