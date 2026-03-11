package com.example.OTP.otpTask.mapper;


import com.example.OTP.otpTask.dao.enitity.OtpEntity;
import com.example.OTP.otpTask.dto.request.OtpRequest;
import com.example.OTP.otpTask.dto.request.OtpRequestv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OtpMapper {
        OtpEntity dtoToEntity(OtpRequest d);
        OtpEntity dtoToEntity(OtpRequestv d);
    }
