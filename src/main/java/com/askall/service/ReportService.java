package com.askall.service;

import com.askall.modal.Report;
import com.askall.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Yeni bir rapor oluştur
    public Report createReport(UUID reporterId, UUID reportedUserId, UUID entityId, Report.EntityType entityType, String reason) {
        Report report = new Report();
        report.setReporterId(reporterId);
        report.setReportedUserId(reportedUserId);
        report.setEntityId(entityId);
        report.setEntityType(entityType);
        report.setReason(reason);
        return reportRepository.save(report);
    }

    // Reporter'ın yaptığı tüm raporları listele
    public List<Report> getReportsByReporter(UUID reporterId) {
        return reportRepository.findByReporterId(reporterId);
    }

    // Belirli bir kullanıcıya karşı yapılan raporları getir
    public List<Report> getReportsByReportedUser(UUID reportedUserId) {
        return reportRepository.findByReportedUserId(reportedUserId);
    }

    // Belirli bir entity türü ve ID'ye göre raporları getir
    public List<Report> getReportsByEntity(Report.EntityType entityType, UUID entityId) {
        return reportRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    // Tüm raporları sıralı şekilde getir
    public List<Report> getAllReports() {
        return reportRepository.findAllByOrderByCreatedAtDesc();
    }
}
