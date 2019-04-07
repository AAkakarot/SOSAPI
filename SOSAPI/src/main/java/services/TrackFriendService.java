package services;

import common.entity.UserPeronalInfo;
import dao.UserPersonalInfoDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.network.NetworkUtilHelper;

@Service
public class TrackFriendService {
    @Autowired
    TrackFriendServiceHelper trackFriendServiceHelper;
    @Autowired
    NetworkUtilHelper networkUtilHelper;

    public JSONObject sendNotification(String myphn,String data,String phonenumber) throws Exception{
        JSONObject jsonObject=new JSONObject();
//        boolean userRegistraionInfo =verifyOtpServiceHelper.verifyOtp(phonenumber,otp);
        UserPeronalInfo userPeronalInfo=trackFriendServiceHelper.getUserPersonalInfo(phonenumber);

        String s[]=userPeronalInfo.getAllphonenumber().split(" ");
        int j=1;
        for(int i=0;i<Integer.parseInt(s[0]);i++){
            j++;
            String phn=s[j++];
            if(phn.equals(myphn)){
                networkUtilHelper.addNotificationKey(data, userPeronalInfo.getKeyForFirebase());
                jsonObject.put("isallowed", true);
                return jsonObject;
            }
        }


        jsonObject.put("isallowed", false);

        return jsonObject;
    }


}
