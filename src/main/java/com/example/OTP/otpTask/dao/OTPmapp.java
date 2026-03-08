package com.example.OTP.otpTask.dao;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OTPmapp {
    OtpEntity dtoToEntity(OTPRequestDto d);
    OtpEntity dtoToEntity(OTPRequestdtov d);
}
