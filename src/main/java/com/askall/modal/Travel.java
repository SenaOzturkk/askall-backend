package com.askall.modal;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "travel_id", nullable = false, updatable = false)
    private UUID travelId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "destination_latitude", nullable = false)
    private Double destinationLatitude;

    @Column(name = "destination_longitude", nullable = false)
    private Double destinationLongitude;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDING;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    // Getter ve Setter metotları
    public UUID getTravelId() {
        return travelId;
    }

    public void setTravelId(UUID travelId) {
        this.travelId = travelId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
