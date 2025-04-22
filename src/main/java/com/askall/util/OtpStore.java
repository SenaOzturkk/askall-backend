package com.askall.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpStore {
    private final Map<String, OtpData> otpMap = new ConcurrentHashMap<>();

    public void saveOtp(String phoneNumber, String otp, int expireMinutes) {
        otpMap.put(phoneNumber, new OtpData(otp, LocalDateTime.now().plusMinutes(expireMinutes)));
    }

    public String getOtp(String phoneNumber) {
        OtpData data = otpMap.get(phoneNumber);
        if (data == null || LocalDateTime.now().isAfter(data.expireAt())) {
            otpMap.remove(phoneNumber); // expire olduysa sil
            return null;
        }
        return data.otp();
    }

    public void removeOtp(String phoneNumber) {
        otpMap.remove(phoneNumber);
    }

    private record OtpData(String otp, LocalDateTime expireAt) {}
}

