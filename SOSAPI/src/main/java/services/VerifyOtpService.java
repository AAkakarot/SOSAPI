package services;

import com.sun.xml.internal.ws.util.CompletedFuture;
import common.dto.SmsRequest;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import dao.UserPersonalInfoDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import util.general.SosMath;
import util.general.Stringpool;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import   util.general.Stringpool;
import util.network.HttpUtil;

@Service
public class VerifyOtpService {
    @Autowired
    VerifyOtpServiceHelper verifyOtpServiceHelper;
    @Autowired
    SosMath sosMath;



    public JSONObject verifyOtp(String phonenumber,String otp) throws Exception{
        JSONObject jsonObject=new JSONObject();
        boolean userRegistraionInfo =verifyOtpServiceHelper.verifyOtp(phonenumber,otp);
        if(userRegistraionInfo==true){
            //user is first time
            jsonObject.put("verified","true");
        }
        else {
            jsonObject.put("verified","false");
        }






        return jsonObject;
    }


}
