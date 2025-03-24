package com.askall.service;

import com.askall.modal.Travel;
import com.askall.repository.TravelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    // Yeni bir seyahat kaydı oluştur
    public Travel createTravel(UUID userId, Double destinationLatitude, Double destinationLongitude) {
        Travel travel = new Travel();
        travel.setUserId(userId);
        travel.setDestinationLatitude(destinationLatitude);
        travel.setDestinationLongitude(destinationLongitude);
        return travelRepository.save(travel);
    }

    // Kullanıcıya ait tüm seyahatleri listele
    public List<Travel> getTravelsByUser(UUID userId) {
        return travelRepository.findByUserId(userId);
    }

    // Seyahatin durumunu güncelle
    public Travel updateTravelStatus(UUID travelId, Travel.Status status) {
        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new IllegalArgumentException("Seyahat bulunamadı"));
        travel.setStatus(status);
        return travelRepository.save(travel);
    }

    // Seyahatlerin durumuna göre listele
    public List<Travel> getTravelsByStatus(Travel.Status status) {
        return travelRepository.findByStatus(status);
    }

    // Tüm seyahatleri listele
    public List<Travel> getAllTravels() {
        return travelRepository.findAllByOrderByCreatedAtDesc();
    }
}
