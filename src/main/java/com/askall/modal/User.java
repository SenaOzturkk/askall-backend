package com.askall.modal;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "bio")
    private String bio;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_premium", nullable = false)
    private Boolean isPremium = false;

    @Column(name = "last_active")
    private LocalDateTime lastActive;

    @Column(name = "location_latitude")
    private Double locationLatitude;

    @Column(name = "location_longitude")
    private Double locationLongitude;

    @Column(name = "is_profile_private", nullable = false)
    private Boolean isProfilePrivate = false;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "is_account_suspended", nullable = false)
    private Boolean isAccountSuspended = false;

    @Column(name = "is_account_frozen", nullable = false)
    private Boolean isAccountFrozen = false;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "is_phone_verified", nullable = false)
    private Boolean isPhoneVerified = false;

    @Column(name = "phone_verification_code")
    private String phoneVerificationCode;

    @Column(name = "phone_verification_code_sent_at")
    private LocalDateTime phoneVerificationCodeSentAt;

    @Column(name = "is_phone_verified_for_2fa", nullable = false)
    private Boolean isPhoneVerifiedFor2FA = false;

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public String getPhoneVerificationCode() {
        return phoneVerificationCode;
    }

    public void setPhoneVerificationCode(String phoneVerificationCode) {
        this.phoneVerificationCode = phoneVerificationCode;
    }

    public LocalDateTime getPhoneVerificationCodeSentAt() {
        return phoneVerificationCodeSentAt;
    }

    public void setPhoneVerificationCodeSentAt(LocalDateTime phoneVerificationCodeSentAt) {
        this.phoneVerificationCodeSentAt = phoneVerificationCodeSentAt;
    }

    public Boolean getIsPhoneVerifiedFor2FA() {
        return isPhoneVerifiedFor2FA;
    }

    public void setIsPhoneVerifiedFor2FA(Boolean isPhoneVerifiedFor2FA) {
        this.isPhoneVerifiedFor2FA = isPhoneVerifiedFor2FA;
    }
}
