package com.askall.controller;

import com.askall.modal.Travel;
import com.askall.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/travels")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    // Yeni bir seyahat kaydı oluştur
    @PostMapping("/create")
    public ResponseEntity<Travel> createTravel(@RequestParam UUID userId,
                                               @RequestParam Double destinationLatitude,
                                               @RequestParam Double destinationLongitude) {
        Travel travel = travelService.createTravel(userId, destinationLatitude, destinationLongitude);
        return ResponseEntity.ok(travel);
    }

    // Kullanıcıya ait tüm seyahatleri listele
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Travel>> getTravelsByUser(@PathVariable UUID userId) {
        List<Travel> travels = travelService.getTravelsByUser(userId);
        return ResponseEntity.ok(travels);
    }

    // Seyahatin durumunu güncelle
    @PutMapping("/update-status/{travelId}")
    public ResponseEntity<Travel> updateTravelStatus(@PathVariable UUID travelId,
                                                     @RequestParam Travel.Status status) {
        Travel travel = travelService.updateTravelStatus(travelId, status);
        return ResponseEntity.ok(travel);
    }

    // Seyahatin durumuna göre filtrele
    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Travel>> getTravelsByStatus(@PathVariable Travel.Status status) {
        List<Travel> travels = travelService.getTravelsByStatus(status);
        return ResponseEntity.ok(travels);
    }

    // Tüm seyahatleri listele
    @GetMapping("/all")
    public ResponseEntity<List<Travel>> getAllTravels() {
        List<Travel> travels = travelService.getAllTravels();
        return ResponseEntity.ok(travels);
    }
}
