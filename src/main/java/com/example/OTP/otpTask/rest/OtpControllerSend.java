package com.example.OTP.otpTask.rest;

import com.example.OTP.otpTask.dto.request.OtpRequest;
import com.example.OTP.otpTask.dto.request.OtpRequestv;
import com.example.OTP.otpTask.dto.response.OtpResponse;
import com.example.OTP.otpTask.service.OTPServicee;
import com.example.OTP.otpTask.service.OTPverifyy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OtpControllerSend {
    private final OTPServicee otpService;
    private final OTPverifyy otPverifyy;


//    @PostMapping("/send")
//    public OtpResponse sendOtp(@RequestBody OtpRequest d){
//        return otpService.sendOtp(d);
//    }
//
//    public String checkCode(@RequestBody OtpRequestv d){
//        return otPverifyy.verifyOtp(d);
//    }



    @PostMapping("/send")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody OtpRequest request) {
        // Servisə deyirik: "Bu nömrəyə bax, lazımdırsa kod göndər"
        OtpResponse response = otpService.sendOtp(request);

        // Cavabı (Status, blok vaxtı və s.) istifadəçiyə qaytarırıq
        return ResponseEntity.ok(response);
    }




    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequestv verifyRequest) {
        // Servisə deyirik: "Bu nömrə ilə bu kodu yoxla"
        String result = otPverifyy.verifyOtp(verifyRequest.getPhoneNumber(), verifyRequest.getOtpCode());

        if (result.contains("Təbriklər")) {
            return ResponseEntity.ok(result); // 200 OK
        } else {
            return ResponseEntity.badRequest().body(result); // 400 Bad Request
        }
    }
}
