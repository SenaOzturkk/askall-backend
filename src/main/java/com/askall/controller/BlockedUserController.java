package com.askall.controller;

import com.askall.modal.BlockedUser;
import com.askall.service.BlockedUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blocked-users")
public class BlockedUserController {

    private final BlockedUserService blockedUserService;

    public BlockedUserController(BlockedUserService blockedUserService) {
        this.blockedUserService = blockedUserService;
    }

    @PostMapping("/block")
    public ResponseEntity<BlockedUser> blockUser(@RequestParam UUID blockerId, @RequestParam UUID blockedId) {
        BlockedUser blockedUser = blockedUserService.blockUser(blockerId, blockedId);
        return ResponseEntity.ok(blockedUser);
    }

    @GetMapping("/{blockerId}")
    public ResponseEntity<List<BlockedUser>> getBlockedUsers(@PathVariable UUID blockerId) {
        List<BlockedUser> blockedUsers = blockedUserService.getBlockedUsers(blockerId);
        return ResponseEntity.ok(blockedUsers);
    }

    @GetMapping("/is-blocked")
    public ResponseEntity<Boolean> isUserBlocked(@RequestParam UUID blockerId, @RequestParam UUID blockedId) {
        boolean isBlocked = blockedUserService.isUserBlocked(blockerId, blockedId);
        return ResponseEntity.ok(isBlocked);
    }

    @DeleteMapping("/unblock")
    public ResponseEntity<Void> unblockUser(@RequestParam UUID blockerId, @RequestParam UUID blockedId) {
        blockedUserService.unblockUser(blockerId, blockedId);
        return ResponseEntity.noContent().build();
    }
}
