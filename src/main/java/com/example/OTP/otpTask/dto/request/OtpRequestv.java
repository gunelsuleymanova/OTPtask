package com.example.OTP.otpTask.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpRequestv {
    private String phoneNumber;
    private String otpCode;
}
