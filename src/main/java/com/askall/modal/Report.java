package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private UUID reportId;

    @Column(name = "reporter_id", nullable = false)
    private UUID reporterId;

    @Column(name = "reported_user_id", nullable = false)
    private UUID reportedUserId;

    @Column(name = "entity_id")
    private UUID entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public enum EntityType {
        QUESTION, ANSWER, COMMENT, MESSAGE, PROFILE
    }

    // Getter ve Setter metotları
    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }

    public UUID getReporterId() {
        return reporterId;
    }

    public void setReporterId(UUID reporterId) {
        this.reporterId = reporterId;
    }

    public UUID getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(UUID reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
