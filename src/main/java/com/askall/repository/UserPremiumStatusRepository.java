package com.askall.repository;

import com.askall.modal.UserPremiumStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPremiumStatusRepository extends JpaRepository<UserPremiumStatus, UUID> {

    // Kullanıcı ID'sine göre premium durumu bulma
    Optional<UserPremiumStatus> findByUserId(UUID userId);
}
