package com.askall.repository;

import com.askall.modal.AskCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AskCreditRepository extends JpaRepository<AskCredit, UUID> {
    Optional<AskCredit> findByUserId(UUID userId);
}
