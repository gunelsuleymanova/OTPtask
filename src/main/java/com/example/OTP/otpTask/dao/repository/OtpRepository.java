package com.example.OTP.otpTask.dao.repository;

import com.example.OTP.otpTask.dao.enitity.OtpEntity;
import com.example.OTP.otpTask.util.OtpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity,Long> {

    Optional<OtpEntity> findByPhoneNumber(String phoneNumber);
    }//optional nullpointerin onunuu alir
