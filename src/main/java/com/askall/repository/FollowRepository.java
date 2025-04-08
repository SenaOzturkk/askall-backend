package com.askall.repository;

import com.askall.modal.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    Optional<Follow> findByFollowerId(UUID followerId); // Kullanıcının takip ettiklerini getirir

    Optional<Follow> findByFollowingId(UUID followingId); // Kullanıcının takipçilerini getirir

    boolean existsByFollowerIdAndFollowingId(UUID followerId, UUID followingId); // Kullanıcı takip ediyor mu?

    Optional<Follow> findByFollowerIdAndFollowingId(UUID followerId, UUID followingId);
}
