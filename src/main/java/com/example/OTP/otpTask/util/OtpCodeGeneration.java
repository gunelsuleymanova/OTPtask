package com.example.OTP.otpTask.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpCodeGeneration {


    public static String otpCode(){
        Random rand= new Random();
        Integer code =rand.nextInt(1000,10000);
        return code.toString();
    }


}
