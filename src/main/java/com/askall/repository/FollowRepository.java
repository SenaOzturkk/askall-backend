package com.askall.repository;

import com.askall.modal.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    List<Follow> findByFollowerId(UUID followerId); // Kullanıcının takip ettiklerini getirir

    List<Follow> findByFollowingId(UUID followingId); // Kullanıcının takipçilerini getirir

    boolean existsByFollowerIdAndFollowingId(UUID followerId, UUID followingId); // Kullanıcı takip ediyor mu?

    void deleteByFollowerIdAndFollowingId(UUID followerId, UUID followingId); // Takibi bırak
}
