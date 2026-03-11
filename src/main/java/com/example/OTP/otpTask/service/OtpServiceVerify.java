package com.example.OTP.otpTask.service;

import com.example.OTP.otpTask.dao.enitity.OtpEntity;
import com.example.OTP.otpTask.dao.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpServiceVerify {

    private final OtpRepository otpRepository;
    public ResponseEntity<String> sendVerify(String phoneNumber,String otpCode){
        var otp=otpRepository.findByPhoneNumber(phoneNumber);
        if(otp.isEmpty()){
            return ResponseEntity.badRequest().body("telefon nomresi tapilmadi");

        }
        OtpEntity otpp =otp.get();
        if(otpp.getExpiryDate().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("otp kodun muddeti bitib");

        }
        if(!otpp.getOtpCode().equals(otpCode)){
            return ResponseEntity.badRequest().body("otp kodu sehvdir");

        }
        otpRepository.delete(otpp);
        return ResponseEntity.ok("sms ugurla dogrulandi");
    }





}
