package com.example.OTP.otpTask.dao.enitity;

import com.example.OTP.otpTask.util.OtpStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "otp_entity")
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String OtpCode;
    private Integer sendCount;          // 5
    private LocalDateTime blocktime;    // block oldugu zaman
    private LocalDateTime expiryDate;   //kodun kecerlilik muddeti
    @Enumerated(EnumType.STRING)
    private OtpStatus otpStatus;        //enum

    public OtpEntity(String phoneNumber, String otpCode, OtpStatus otpStatus, Integer sendCount, LocalDateTime expiryDate, LocalDateTime blocktime) {
        this.phoneNumber = phoneNumber;
        OtpCode = otpCode;
        this.otpStatus = otpStatus;
        this.sendCount = sendCount;
        this.expiryDate = expiryDate;
        this.blocktime = blocktime;
    }


}
