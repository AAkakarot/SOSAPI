package util.network;

import com.google.gson.Gson;

import common.dto.SmsRequest;
import controller.SignUpController;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import util.general.Stringpool;
import util.logger.MyLogManager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class NetworkUtilHelper {
@Autowired
    HttpUtil httpUtil;
    private static MyLogManager myLogManager =new MyLogManager(NetworkUtilHelper.class);
final Gson gson=new Gson();

public String sendSms(SmsRequest smsRequest){
    try{
    String params=makeSmsParams(smsRequest);
    String smsurl=makeSmsUrl(smsRequest);
    CompletableFuture<String> sendsms=CompletableFuture.supplyAsync(()->{
     return  httpUtil.doPostWithHeaders(null, smsurl, params, MediaType.APPLICATION_JSON_VALUE);
    });
return sendsms.get(Long.parseLong(Stringpool.REQUEST_TIMEOUT),TimeUnit.MILLISECONDS);}
catch (Exception e){
        myLogManager.error("Send otp failed");
}
return null;
}
String makeSmsParams(SmsRequest smsRequest){
    String params=gson.toJson(smsRequest);
return params;
}
String makeSmsUrl(SmsRequest smsRequest){
    Class reflection=smsRequest.getClass();
        String url="";
        url=Stringpool.smsgateway.url+"?";
        try {
            Field field[] = reflection.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                field[i].setAccessible(true);
                url = url + field[i].getName() + "=" + field[i].get(smsRequest).toString();
                if(i!=field.length-1){url=url+"&";}
            }
        }
        catch (Exception e){

        }
        return url;
    }


    public String addNotificationKey(
            String msg,String key    )
            throws IOException, JSONException {

        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            // HTTP request header
            //  con.setRequestProperty("project_id", senderId);
            con.setRequestProperty("Content-Type", "application/json");

            con.setRequestProperty("Authorization", Stringpool.firebasekey);
            con.setRequestMethod("POST");
            con.connect();
            String postJsonData = "{\"to\": \""+key+"\", \"data\":"+" {\"message\": \""+msg+"\"}"+"}";

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postJsonData);
            wr.flush();
            wr.close();

            // Read the response into a string
            int status =con.getResponseCode();


            String err=con.getResponseMessage();


            // Parse the JSON string and return the notification key

            return "bad";
        }
        catch (Exception e){

            return "hgvv";
        }
    }
}

