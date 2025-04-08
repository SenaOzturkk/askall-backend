package com.askall.service;

import com.askall.dto.ApiResponse;
import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse<Object> createUser(User user) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            return ApiResponse.success(HttpStatus.OK, "Email already exists: " + user.getEmail(),null);
        }

        Optional<User> existingUserByPhone = userRepository.findByPhone(user.getPhone());
        if (existingUserByPhone.isPresent()) {
            return ApiResponse.success(HttpStatus.OK,"Phone number already exists: " + user.getPhone(),null);
        }

        if (!user.getPhone().matches("\\d+")) {
            return ApiResponse.success(HttpStatus.OK,"Invalid phone number format. Only numbers are allowed.",null);
        }

        String randomUsername = RandomUsernameGenerator.generateRandomUsername(10);
        user.setUsername("user_" + randomUsername);
        user.setLastActive(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return ApiResponse.success(HttpStatus.OK, "User created successfully", savedUser);
    }

    public Optional<User> deleteUser(UUID userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        user.ifPresent(userRepository::delete);
        return user;
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findByUserId(userId);
    }


}
