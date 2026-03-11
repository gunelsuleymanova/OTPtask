package com.example.OTP.otpTask.dto.response;

import com.example.OTP.otpTask.util.OtpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpResponse {

    private  OtpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime blockTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime expTime;  //bu timeler null gelse gosterme


}
