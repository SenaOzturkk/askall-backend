package com.askall.controller;

import com.askall.modal.Report;
import com.askall.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Yeni bir rapor oluştur
    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestParam UUID reporterId,
                                               @RequestParam UUID reportedUserId,
                                               @RequestParam UUID entityId,
                                               @RequestParam Report.EntityType entityType,
                                               @RequestParam String reason) {
        Report report = reportService.createReport(reporterId, reportedUserId, entityId, entityType, reason);
        return ResponseEntity.ok(report);
    }

    // Reporter tarafından yapılmış raporları listele
    @GetMapping("/by-reporter/{reporterId}")
    public ResponseEntity<List<Report>> getReportsByReporter(@PathVariable UUID reporterId) {
        List<Report> reports = reportService.getReportsByReporter(reporterId);
        return ResponseEntity.ok(reports);
    }

    // Belirli bir kullanıcıya karşı yapılmış raporları listele
    @GetMapping("/by-reported/{reportedUserId}")
    public ResponseEntity<List<Report>> getReportsByReportedUser(@PathVariable UUID reportedUserId) {
        List<Report> reports = reportService.getReportsByReportedUser(reportedUserId);
        return ResponseEntity.ok(reports);
    }

    // Belirli bir entity türü ve ID'sine göre raporları listele
    @GetMapping("/by-entity")
    public ResponseEntity<List<Report>> getReportsByEntity(@RequestParam Report.EntityType entityType,
                                                           @RequestParam UUID entityId) {
        List<Report> reports = reportService.getReportsByEntity(entityType, entityId);
        return ResponseEntity.ok(reports);
    }

    // Tüm raporları listele
    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}
