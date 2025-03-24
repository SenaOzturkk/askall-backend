package com.askall.service;

import com.askall.modal.Follow;
import com.askall.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public List<Follow> getFollowers(UUID userId) {
        return followRepository.findByFollowingId(userId);
    }

    public List<Follow> getFollowing(UUID userId) {
        return followRepository.findByFollowerId(userId);
    }

    public boolean isFollowing(UUID followerId, UUID followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    public Follow followUser(UUID followerId, UUID followingId) {
        if (isFollowing(followerId, followingId)) {
            throw new IllegalArgumentException("Kullanıcı zaten takip ediliyor!");
        }

        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowingId(followingId);

        return followRepository.save(follow);
    }

    public void unfollowUser(UUID followerId, UUID followingId) {
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);
    }
}
