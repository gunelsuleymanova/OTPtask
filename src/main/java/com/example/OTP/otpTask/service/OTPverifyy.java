package com.example.OTP.otpTask.service;

import com.example.OTP.otpTask.dao.repository.OtpRepository;
import com.example.OTP.otpTask.util.OtpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OTPverifyy {
    private final OtpRepository otpRepository;

    public String verifyOtp(String phoneNumber ,String userCode){
        var data = otpRepository.findByPhoneNumber(phoneNumber);
        if(data.isEmpty()){
            return "Xəta: Belə bir nömrə üçün aktiv OTP tapılmadı!";
        }
        var dat = data.get();

        if(dat.getOtpStatus()== OtpStatus.BLOCK){
            return "Hesabınız blokdadır. Zəhmət olmasa gözləyin.";
        }


        if(dat.getExpiryDate().isBefore(LocalDateTime.now())){
            otpRepository.delete(dat);
            return "Xəta: Kodun vaxtı bitib, yenidən kod istəyin.";
        }

        if(dat.getOtpCode().equals(userCode)){
            otpRepository.delete(dat);
            return "Təbriklər! Kod təsdiqləndi.";
        }else {
            return "Xəta: Daxil etdiyiniz kod yanlışdır!";
        }









    }
}
