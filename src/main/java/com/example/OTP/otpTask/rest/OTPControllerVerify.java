package com.example.OTP.otpTask.rest;

import com.example.OTP.otpTask.dao.OTPRequestDto;
import com.example.OTP.otpTask.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v8")
@RequiredArgsConstructor
public class OTPControllerVerify {
    private final OTPService otpService;


    @PostMapping
    public String postNumber(@RequestBody OTPRequestDto d){
        return otpService.postOtp(d);
    }

}
