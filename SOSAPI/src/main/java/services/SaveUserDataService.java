package services;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.util.CompletedFuture;
import common.dto.SmsRequest;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import common.entity.VehcilePhoneNumber;
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
import java.util.List;
import java.util.concurrent.CompletableFuture;

import   util.general.Stringpool;
import util.general.UtilMethods;
import util.network.HttpUtil;

@Service
public class SaveUserDataService {
    @Autowired
    SaveUserDataServiceHelper saveUserDataServiceHelper;
    @Autowired
    SosMath sosMath;
    final Gson gson=new Gson();


    public JSONObject addVehcile(String phonenumber,String vehcilelist) throws Exception{
        JSONObject jsonObject=new JSONObject();
//        boolean userRegistraionInfo =verifyOtpServiceHelper.verifyOtp(phonenumber,otp);
        ArrayList<String> vehciles=UtilMethods.convertToVehcileArray(vehcilelist);


        ArrayList<VehcilePhoneNumber> vehcilePhoneNumberArrayList=new ArrayList<VehcilePhoneNumber>();

        for(int i=0;i<vehciles.size();i++){
            VehcilePhoneNumber vehcilePhoneNumber=VehcilePhoneNumber.build(phonenumber,vehciles.get(i),Stringpool.idType.VEHCILETYPE);
            vehcilePhoneNumberArrayList.add(vehcilePhoneNumber);
        }

        saveUserDataServiceHelper.updateVehciles(phonenumber,Stringpool.idType.VEHCILETYPE,vehcilePhoneNumberArrayList,vehcilelist);
        jsonObject.put("isdone","true");

/*
        UserPeronalInfo userPeronalInfo=restoreServiceHelper.restoreAccountUsePersonal(phonenumber);

        if(userPeronalInfo==null){
            jsonObject.put("exists","false");
        }
        else{
            jsonObject.put("exists","true");
            jsonObject.put("userinfo",gson.toJson(userPeronalInfo));
        }*/
     /*   if(userRegistraionInfo==true){
            //user is first time
            jsonObject.put("verified","true");
        }
        else {
            jsonObject.put("verified","false");
        }*/






        return jsonObject;
    }
    public JSONObject addPhone(String phonenumber,String phonelist,String permission) throws Exception{
        JSONObject jsonObject=new JSONObject();
//        boolean userRegistraionInfo =verifyOtpServiceHelper.verifyOtp(phonenumber,otp);

        saveUserDataServiceHelper.updatePhone(phonenumber,phonelist,permission);

        jsonObject.put("isdone","true");





        return jsonObject;
    }


}
