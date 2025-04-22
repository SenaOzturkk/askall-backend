package com.askall.service;

import com.askall.dto.ApiResponse;
import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.repository.UserRepository;
import com.askall.util.JwtTokenUtil;
import com.askall.util.OtpStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
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



    @Autowired
    private OtpStore otpStore;

    public ApiResponse<Object> initiatePhoneVerification(String phoneNumber) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStore.saveOtp(phoneNumber, otp, 5); // 5 dakika geçerli

        System.out.println("OTP for " + phoneNumber + ": " + otp);

        //  sendSms(phoneNumber, "AskAll OTP Kodunuz: " + otp);
        return ApiResponse.success(HttpStatus.OK, "OTP gönderildi..", otp);
    }

    public ResponseEntity<ApiResponse<Object>> verifyPhoneNumber(String phoneNumber, String code) {
        String storedOtp = otpStore.getOtp(phoneNumber);

        if (storedOtp == null) {
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "OTP expired or invalid",null));
        }

        if (!storedOtp.equals(code)) {
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Invalid OTP code", null));
        }

        otpStore.removeOtp(phoneNumber);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Successfully", jwtTokenUtil.generateToken(phoneNumber)));
    }




}
