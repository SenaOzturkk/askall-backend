package com.askall.service;

import com.askall.modal.Answer;
import com.askall.modal.Follow;
import com.askall.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Optional<Follow> getFollowers(UUID userId) {
        return followRepository.findByFollowingId(userId);
    }

    public Optional<Follow> getFollowing(UUID userId) {
        return followRepository.findByFollowerId(userId);
    }

    public boolean isFollowing(UUID followerId, UUID followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    public Follow followUser(UUID followerId, UUID followingId) {
        if (isFollowing(followerId, followingId)) {
            throw new IllegalStateException("Kullanıcı zaten takip ediliyor!");  // Hata mesajını daha anlamlı yaptık
        }

        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowingId(followingId);

        return followRepository.save(follow);
    }

    public Optional<Follow> unfollowUser(UUID followerId, UUID followingId) {
        Optional<Follow> follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);

        if (follow.isEmpty()) {
            throw new RuntimeException("Follow relationship not found");
        }

        Follow followToUpdate = follow.get();
        followToUpdate.setDeleted(true);
        followRepository.save(followToUpdate);

        return follow;
    }


}
