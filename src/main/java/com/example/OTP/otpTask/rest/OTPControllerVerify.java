package com.example.OTP.otpTask.rest;

import com.example.OTP.otpTask.dto.request.OtpRequest;
import com.example.OTP.otpTask.dto.response.OtpResponse;
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
    public OtpResponse postNumber(@RequestBody OtpRequest d){
        return otpService.sendOtp(d);

    }

}
