package services;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.util.CompletedFuture;
import common.dto.SmsRequest;
import common.entity.AllvehcileCrash;
import common.entity.CrashVehcilePhoneMapping;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import dao.AllCrashDao;
import dao.UserPersonalInfoDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.general.SosMath;
import util.general.Stringpool;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import   util.general.Stringpool;
import util.general.UtilMethods;
import util.network.HttpUtil;
import util.network.NetworkUtilHelper;

@Service
public class ThreeFragmentService {
    @Autowired
    ThreeFragmentServiceHelper threeFragmentServiceHelper;
    @Autowired
    SosMath sosMath;
    @Autowired
    NetworkUtilHelper networkUtilHelper;



    final Gson gson=new Gson();


    public JSONObject uploadFileAndSendSos(String phonenumber, String data, MultipartFile multipartFile) throws Exception{
        JSONObject jsonObject=new JSONObject();
        String srr[]=data.split("$$");
        File newFile =new File(Stringpool.imgdir+srr[1]);
        UtilMethods.copyFile(multipartFile, newFile);

        AllvehcileCrash allvehcileCrash=new AllvehcileCrash();
        allvehcileCrash.setData(data);
        allvehcileCrash.setVehcileno(srr[0]);

        threeFragmentServiceHelper.saveAllVehcileCrash(allvehcileCrash);

        UserPeronalInfo userPeronalInfo=threeFragmentServiceHelper.getUserPersonalInfo(phonenumber);
        ArrayList<String>  numbers=new ArrayList<String>();

        String s[]=userPeronalInfo.getAllphonenumber().split(" ");
        int j=1;
        for(int i=0;i<Integer.parseInt(s[0]);i++){
            j++;
            String phn=s[j++];
            String second=phonenumber.replace("phonenumbertoreplace",phn );
            numbers.add(phn);
            UserPeronalInfo userPeronalInfo1=threeFragmentServiceHelper.getUserPersonalInfo(phn);
            CrashVehcilePhoneMapping crashVehcilePhoneMapping=new CrashVehcilePhoneMapping();
            crashVehcilePhoneMapping.setId(allvehcileCrash.getId());
            crashVehcilePhoneMapping.setPhonenumber(phn);
            threeFragmentServiceHelper.saveAllVehcilePhoneMapping(crashVehcilePhoneMapping);
            new Thread(() -> {
                try {
                    networkUtilHelper.addNotificationKey(second, userPeronalInfo1.getKeyForFirebase());
                }
                catch (Exception e){

                }

            });

        }


        if(userPeronalInfo.getPremiumstatus()==1){
        SmsRequest smsRequest=SmsRequest.build(Stringpool.smsgateway.apikey,Stringpool.smsgateway.senderid,Stringpool.smsgateway.channel.transactinal,
                Stringpool.smsgateway.dcs.zero,Stringpool.smsgateway.flashsms.zero,numbers,"User with phone number "+phonenumber +" needs help",Stringpool.smsgateway.routeid.defaultid);
        networkUtilHelper.sendSms(smsRequest);
        }
        return jsonObject;
    }


}
