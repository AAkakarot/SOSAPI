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
public class SignUpService {
    @Autowired
    SignUpServiceHelper signUpServiceHelper;
    @Autowired
    SosMath sosMath;



    public JSONObject isRegistered(String phonenumber) throws Exception{
        JSONObject jsonObject=new JSONObject();
        UserRegistraionInfo userRegistraionInfo =signUpServiceHelper.isAlreadyRegister(phonenumber);
        if(userRegistraionInfo==null){
        //user is first time
         userRegistraionInfo=   nonRegisteredUser(phonenumber);
            jsonObject.put("newuser","true");
        }
        else {
            registeredUser(userRegistraionInfo);
            jsonObject.put("newuser","false");
        }

        ArrayList<String>  numbers=new ArrayList<String>();
        numbers.add(phonenumber);

        SmsRequest smsRequest=SmsRequest.build(Stringpool.smsgateway.apikey,Stringpool.smsgateway.senderid,Stringpool.smsgateway.channel.transactinal,
                Stringpool.smsgateway.dcs.zero,Stringpool.smsgateway.flashsms.zero,numbers,userRegistraionInfo.getOtp(),Stringpool.smsgateway.routeid.defaultid);
        signUpServiceHelper.sendOtp(smsRequest);




        return jsonObject;
    }

    public void registeredUser(UserRegistraionInfo userRegistraionInfo){
        userRegistraionInfo.setOtp(sosMath.getRandomString(6));
        signUpServiceHelper.updateRegistration(userRegistraionInfo);
    }
    public UserRegistraionInfo nonRegisteredUser(String phonenumber){

        UserRegistraionInfo userRegistraionInfo=UserRegistraionInfo.build(phonenumber);
        userRegistraionInfo.setOtp(sosMath.getRandomString(6));
        signUpServiceHelper.saveRegistration(userRegistraionInfo);

        UserPeronalInfo userPeronalInfo=UserPeronalInfo.build(phonenumber);
        signUpServiceHelper.savePersonalInfo(userPeronalInfo);

        //new otp send


        return userRegistraionInfo;
    }
}
