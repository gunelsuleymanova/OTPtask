package com.example.OTP.otpTask.service;

import com.example.OTP.otpTask.dao.OTPRepository;
import com.example.OTP.otpTask.dao.OTPRequestDto;
import com.example.OTP.otpTask.dao.OTPmapp;
import com.example.OTP.otpTask.dao.OtpEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final OTPRepository otpRepository;
    private final OTPmapp otPmapp;

    public String postOtp(OTPRequestDto e){
        OtpEntity d= new OtpEntity();
        otpRepository.save(otPmapp.dtoToEntity(e));

        return generate();
    }

//    private Integer generateotpkod(){
//            Random random = new Random();
//            Integer eded = random.nextInt(4);
//            System.out.println(eded);
//            return eded;
//        }


        private String generate() {
            Random random = new Random();
            int eded = random.nextInt(4);
            String ededString = String.valueOf(eded);
            System.out.println(ededString);
            return ededString;


        }

















}
