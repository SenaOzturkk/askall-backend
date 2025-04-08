package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.BlockedUser;
import com.askall.modal.Question;
import com.askall.service.BlockedUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/blocked-users")
public class BlockedUserController {

    private final BlockedUserService blockedUserService;

    public BlockedUserController(BlockedUserService blockedUserService) {
        this.blockedUserService = blockedUserService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> blockUser(@RequestParam UUID blockerId, @RequestParam UUID blockedId) {
        ApiResponse<Object> blockedUser =  blockedUserService.blockUser(blockerId, blockedId);
        return ResponseEntity.status(blockedUser.getStatus()).body(blockedUser);
    }

    @GetMapping("/{blockerId}")
    public ResponseEntity<ApiResponse<Object>> getBlockedUsers(@PathVariable UUID blockerId) {
        ApiResponse<Object> blockedUsers = blockedUserService.getBlockedUsers(blockerId);
        return ResponseEntity.status(blockedUsers.getStatus()).body(blockedUsers);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> unblockUser(@RequestParam UUID blockerId, @RequestParam UUID blockedId) {
        Optional<BlockedUser> deletedBlockedUser = blockedUserService.unblockUser(blockerId, blockedId);

        if (deletedBlockedUser.isPresent()) {
            return ResponseEntity.ok(
                    ApiResponse.success(HttpStatus.OK, "Unblock successfully", deletedBlockedUser.get())
            );
        } else {
            return ResponseEntity.ok(
                    ApiResponse.success(HttpStatus.OK, "Unblock not found", deletedBlockedUser)
            );
        }
    }

}
