package com.example.OTP.otpTask.service;

import com.example.OTP.otpTask.dao.enitity.OtpEntity;
import com.example.OTP.otpTask.dao.repository.OtpRepository;
import com.example.OTP.otpTask.dto.request.OtpRequest;
import com.example.OTP.otpTask.dto.response.OtpResponse;
import com.example.OTP.otpTask.util.OtpCodeGeneration;
import com.example.OTP.otpTask.util.OtpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OTPServicee {
    private final OtpRepository otpRepository;
    private final SendMailService sendMailService;

    public OtpResponse sendOtp(OtpRequest d){
        var  otp= otpRepository.findByPhoneNumber(d.phoneNumber());
        if(otp.isPresent()){ //eger nomre bazada varsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
            var data = otp.get(); //bazadaki melumati qutuya qoyduq
          if(data.getOtpStatus()==OtpStatus.BLOCK){
              //blok vaxdi hele bitmeyibse hele 2 deqiqeden az vaxd gecibse
              if(data.getBlocktime().isAfter(LocalDateTime.now())){
                  return new OtpResponse(data.getOtpStatus(),data.getBlocktime(),data.getExpiryDate());
              }
              //blok vaxdi bitibse meselen 10 deqiqe kecibse
              else {
                  otpRepository.delete(data);
                  return firstTimeOtp(d);

              }
          }

//
          if(data.getSendCount()>=5){
              //limit dolub onu bloklayaq
              data.setOtpStatus(OtpStatus.BLOCK);
              data.setBlocktime(LocalDateTime.now().plusMinutes(5));
              data.setExpiryDate(null);
              otpRepository.save(data);
              return new OtpResponse(data.getOtpStatus(),data.getBlocktime(),data.getExpiryDate());
          }
          else {
              //HER sey okaydi yeniden kod gondere bilerik
              //yeniden kod yaradiriq
              String newCode= OtpCodeGeneration.otpCode();
              sendMailService.sendEmail(data.getPhoneNumber(), newCode);
              //melumatlari yenileyirik
              data.setOtpCode(newCode);
              data.setSendCount(data.getSendCount()+1);
              data.setExpiryDate(LocalDateTime.now().plusMinutes(2));
              data.setOtpStatus(OtpStatus.PENDING);
              System.out.println("YENİ SMS (Təkrar): " + newCode);
              otpRepository.save(data);
              return  new OtpResponse(data.getOtpStatus(),data.getBlocktime(),data.getExpiryDate());

          }




        }else {  //eger nomre bazada yoxdursaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
            return firstTimeOtp(d);
        }




    }









    //======================================================================

    private OtpResponse firstTimeOtp(OtpRequest d){
        String newGeneratedCode = OtpCodeGeneration.otpCode();  //yeni kod generate etdik
        sendMailService.sendEmail(d.phoneNumber(), newGeneratedCode);
        OtpEntity entity = OtpEntity.builder().phoneNumber(d.phoneNumber()).OtpCode(newGeneratedCode).otpStatus(OtpStatus.PENDING).sendCount(1).expiryDate(LocalDateTime.now().plusMinutes(2)).build();  //entity yaratdiq
        otpRepository.save(entity); //entityni save edirik
        System.out.println("İstifadəçiyə SMS getdi: " + newGeneratedCode); //log kimi consola mesaj yaziriq
        return new OtpResponse(entity.getOtpStatus(),entity.getBlocktime(),entity.getExpiryDate()); //dto yaradiriq



    }


}
