package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Report;
import com.askall.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createReport(@RequestBody Report report) {
        try {
            // Validate input (optional)
            if (report.getReporterId() == null || report.getReportedUserId() == null || report.getEntityId() == null || report.getReason() == null) {
                return ResponseEntity.ok(ApiResponse.error(HttpStatus.BAD_REQUEST, "All fields must be provided"));
            }

            Report createdReport = reportService.createReport(
                    report.getReporterId(),
                    report.getReportedUserId(),
                    report.getEntityId(),
                    report.getEntityType(),
                    report.getReason()
            );

            ApiResponse<Object> response = ApiResponse.success(HttpStatus.CREATED, "Rapor başarıyla oluşturuldu", createdReport);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Rapor oluşturulurken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }


    // Reporter tarafından yapılmış raporları listele
    @GetMapping("/by-reporter/{reporterId}")
    public ResponseEntity<ApiResponse<Object>> getReportsByReporter(@PathVariable UUID reporterId) {
        try {
            List<Report> reports = reportService.getReportsByReporter(reporterId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcının yaptığı raporlar başarıyla getirildi", reports);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Raporlar getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Belirli bir kullanıcıya karşı yapılmış raporları listele
    @GetMapping("/by-reported/{reportedUserId}")
    public ResponseEntity<ApiResponse<Object>> getReportsByReportedUser(@PathVariable UUID reportedUserId) {
        try {
            List<Report> reports = reportService.getReportsByReportedUser(reportedUserId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Hakkında rapor yapılan kullanıcıya ait kayıtlar getirildi", reports);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Raporlar getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Belirli bir entity türü ve ID'sine göre raporları listele
    @GetMapping("/by-entity")
    public ResponseEntity<ApiResponse<Object>> getReportsByEntity(@RequestParam Report.EntityType entityType,
                                                                  @RequestParam UUID entityId) {
        try {
            List<Report> reports = reportService.getReportsByEntity(entityType, entityId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Entity'ye ait raporlar başarıyla getirildi", reports);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Raporlar getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Tüm raporları listele
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllReports() {
        try {
            List<Report> reports = reportService.getAllReports();
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Tüm raporlar başarıyla listelendi", reports);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Raporlar listelenirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }
}
