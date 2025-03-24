package com.askall.repository;

import com.askall.modal.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    // Reporter tarafından yapılan tüm raporları getir
    List<Report> findByReporterId(UUID reporterId);

    // Belirli bir kullanıcıya karşı yapılan tüm raporları getir
    List<Report> findByReportedUserId(UUID reportedUserId);

    // Belirli bir entity türü ve id'sine göre raporları getir
    List<Report> findByEntityTypeAndEntityId(Report.EntityType entityType, UUID entityId);

    // Tüm raporları tarihe göre sıralı olarak getir
    List<Report> findAllByOrderByCreatedAtDesc();
}
