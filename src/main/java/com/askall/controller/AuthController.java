package com.askall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.askall.dto.ApiResponse;
import com.askall.modal.User;
import com.askall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/send-verification")
    public ResponseEntity<ApiResponse<Object>> sendVerification(
            @RequestParam String phoneNumber) {

        System.out.println("OTP gönderme isteği alınan telefon numarası: " + phoneNumber);
        ApiResponse<Object> response = userService.initiatePhoneVerification(phoneNumber);
        System.out.println("OTP gönderme yanıtı: " + response.getStatus());

        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @PostMapping("/verify-phone")
    public ResponseEntity<ApiResponse<Object>> verifyPhone(
            @RequestParam  String phoneNumber,
            @RequestParam  String code) {
        ApiResponse<Object> response = userService.verifyPhoneNumber(phoneNumber, code).getBody();
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
