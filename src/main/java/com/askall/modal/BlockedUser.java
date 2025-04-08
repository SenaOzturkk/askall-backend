package com.askall.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "blocked_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"blocker_id", "blocked_id"})
})
@Getter
@Setter
@NoArgsConstructor
public class BlockedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "blocker_id", nullable = false)
    private UUID blockerId;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "unblocked_at")
    private Instant unblockedAt;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public UUID getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(UUID blockerId) {
        this.blockerId = blockerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(UUID blockedId) {
        this.blockedId = blockedId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "blocked_id", nullable = false)
    private UUID blockedId;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    public BlockedUser(UUID blockerId, UUID blockedId) {
        this.blockerId = blockerId;
        this.blockedId = blockedId;
        this.createdAt = Instant.now();
    }


    public Instant getUnblockedAt() {
        return unblockedAt;
    }

    public void setUnblockedAt(Instant unblockedAt) {
        this.unblockedAt = unblockedAt;
    }

}
