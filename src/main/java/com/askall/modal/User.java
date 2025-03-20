package com.askall.modal;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String profilePicture;
    private String bio;
    private LocalDateTime createdAt;
    private Boolean isPremium;
    private LocalDateTime lastActive;

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Boolean getProfilePrivate() {
        return isProfilePrivate;
    }

    public void setProfilePrivate(Boolean profilePrivate) {
        isProfilePrivate = profilePrivate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getAccountSuspended() {
        return isAccountSuspended;
    }

    public void setAccountSuspended(Boolean accountSuspended) {
        isAccountSuspended = accountSuspended;
    }

    public Boolean getAccountFrozen() {
        return isAccountFrozen;
    }

    public void setAccountFrozen(Boolean accountFrozen) {
        isAccountFrozen = accountFrozen;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private Double locationLatitude;
    private Double locationLongitude;
    private Boolean isProfilePrivate;
    private Boolean isDeleted;
    private Boolean isAccountSuspended;
    private Boolean isAccountFrozen;
    private String uuid;
    // Getters and setters
}