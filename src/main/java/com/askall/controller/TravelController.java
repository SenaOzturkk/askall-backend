package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Travel;
import com.askall.service.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    // Yeni bir seyahat kaydı oluştur
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createTravel(@RequestParam UUID userId,
                                                            @RequestParam Double destinationLatitude,
                                                            @RequestParam Double destinationLongitude) {
        try {
            Travel travel = travelService.createTravel(userId, destinationLatitude, destinationLongitude);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.CREATED, "Seyahat başarıyla oluşturuldu", travel);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Seyahat oluşturulurken bir hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Kullanıcıya ait tüm seyahatleri listele
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<ApiResponse<Object>> getTravelsByUser(@PathVariable UUID userId) {
        try {
            List<Travel> travels = travelService.getTravelsByUser(userId);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Kullanıcıya ait seyahatler başarıyla getirildi", travels);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Seyahatler getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Seyahatin durumunu güncelle
    @PutMapping("/update-status/{travelId}")
    public ResponseEntity<ApiResponse<Object>> updateTravelStatus(@PathVariable UUID travelId,
                                                                  @RequestParam Travel.Status status) {
        try {
            Travel travel = travelService.updateTravelStatus(travelId, status);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Seyahat durumu başarıyla güncellendi", travel);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Seyahat durumu güncellenirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Seyahatin durumuna göre filtrele
    @GetMapping("/by-status/{status}")
    public ResponseEntity<ApiResponse<Object>> getTravelsByStatus(@PathVariable Travel.Status status) {
        try {
            List<Travel> travels = travelService.getTravelsByStatus(status);
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Belirtilen duruma ait seyahatler başarıyla getirildi", travels);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Seyahatler getirilirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }

    // Tüm seyahatleri listele
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllTravels() {
        try {
            List<Travel> travels = travelService.getAllTravels();
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "Tüm seyahatler başarıyla listelendi", travels);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Seyahatler listelenirken hata oluştu");
            return ResponseEntity.ok(response);
        }
    }
}
