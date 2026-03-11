package com.example.OTP.otpTask.service;


//import com.example.OTP.Status;
import com.example.OTP.otpTask.dao.enitity.OtpEntity;
import com.example.OTP.otpTask.dao.repository.OtpRepository;
import com.example.OTP.otpTask.dto.request.OtpRequest;
import com.example.OTP.otpTask.dto.response.OtpResponse;
import com.example.OTP.otpTask.exceptions.ActiveOtpException;
import com.example.OTP.otpTask.mapper.OtpMapper;
import com.example.OTP.otpTask.util.OtpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.OTP.otpTask.util.OtpCodeGeneration.*;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final OtpRepository otpRepository;
    private final OtpMapper otPmapp;



    public OtpResponse sendOtp(OtpRequest d){
        var odata = otpRepository.findByPhoneNumber(d.phoneNumber());
        if(odata.isPresent()){
            //datanin dolu oldugu hal
            //data dolu olsa bele bizim hesabimiz bagli ola biler
            var data = odata.get();
            if(data.getOtpStatus().equals(OtpStatus.BLOCK)){
                if(data.getBlocktime().isAfter(LocalDateTime.now())){
                    //indiki vaxdan hele kecmeyibse return et
                    return otpResponse(data);
                }else {
                    //eger vaxdi kecibse o zaman
                    removePhoneNUmber(data);
                    var result= firstTimeOtp(d);
                    return otpResponse(result);

                }

            }else {
                //bu istifadecinin hec bloklanmadigi haldir
                if(data.getSendCount()>=5){
                    data.setBlocktime(LocalDateTime.now().plusMinutes(5)); //5 deqiqe blokladim
                    data.setOtpStatus(OtpStatus.BLOCK);
                    data.setExpiryDate(null);
                    var result= otpRepository.save(data);
                    return otpResponse(result);
                }else {
                    //istifadeci 5 den kicik hal
                    data.setSendCount(data.getSendCount()+1); //hemise ustune bir gelib yaziriq db ye
                    data.setOtpCode(otpCode());
                    var result = otpRepository.save(data);
                    return otpResponse(result);

                }
            }

        }
        //datanin bos oldugu hal
        else {
           var result= firstTimeOtp(d);
           return otpResponse(result);

        }

    }

    private void removePhoneNUmber(OtpEntity e){
        otpRepository.delete(e);
    }

private OtpResponse otpResponse(OtpEntity e){
return new OtpResponse(e.getOtpStatus(),e.getBlocktime(),e.getExpiryDate());

}




//ilk defe gonderilende otp
private OtpEntity firstTimeOtp(OtpRequest d){
       return otpRepository.save(new OtpEntity(d.phoneNumber(),otpCode(),OtpStatus.PENDING,1,null,LocalDateTime.now().plusMinutes(5)));
       // return new OtpEntity(d.phoneNumber(),otpCode(),OtpStatus.PENDING,1,null,LocalDateTime.now().plusMinutes(5));

}











}

//    public String postOtp(OTPRequestDto e){
//        OtpEntity d= new OtpEntity();
//        otpRepository.save(otPmapp.dtoToEntity(e));
//
//        return generate();
//    }


//        private String generate () {
//            Random random = new Random();
//            int eded = random.nextInt(4);
//            String ededString = String.valueOf(eded);
//            System.out.println(ededString);
//            return ededString;
//
//
//        }

    //    private Integer generateotpkod(){
//            Random random = new Random();
//            Integer eded = random.nextInt(4);
//            System.out.println(eded);
//            return eded;
//        }


