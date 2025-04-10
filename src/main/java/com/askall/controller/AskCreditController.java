package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Answer;
import com.askall.modal.AskCredit;
import com.askall.service.AskCreditService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credits")
public class AskCreditController {

    private final AskCreditService askCreditService;

    public AskCreditController(AskCreditService askCreditService) {
        this.askCreditService = askCreditService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getUserCredits(@PathVariable UUID userId) {
        AskCredit  askCredit = askCreditService.getCreditsByUserId(userId);
        ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "askCredit found successfully", askCredit);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> createCredits(@PathVariable UUID userId, @RequestParam int amount) {
        try {
            // Kredi kaydını oluşturma
            AskCredit newCredit = askCreditService.createCredit(userId, amount);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kredi başarıyla oluşturuldu", newCredit);
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            // Eğer veri bütünlüğü ihlali (duplicate key) hatası alınırsa, zaten kaydın mevcut olduğunu bildir
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.BAD_REQUEST, "Bu kullanıcı için kredi kaydı zaten mevcut. Lütfen krediyi güncelleyin.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Diğer hatalar için genel bir hata mesajı
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Beklenmedik bir hata oluştu.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    // Update işlemi: Var olan kredi kaydını günceller.
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> updateCredits(@PathVariable UUID userId, @RequestParam int amount) {
        AskCredit updatedCredit = askCreditService.updateCredits(userId, amount);
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kredi başarıyla güncellendi", updatedCredit);
        return ResponseEntity.ok(response);
    }
}
