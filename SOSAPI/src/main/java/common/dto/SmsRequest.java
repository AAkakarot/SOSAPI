package common.dto;

import java.util.ArrayList;

public class SmsRequest {
    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

  private   String APIKey;
  private   String senderid;
  private   String channel;
  private   String dcs;
  private   String flashsms;
  private   String number;
  private   String text;
  private   String routeid;


    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getDcs() {
        return dcs;
    }

    public void setDcs(String dcs) {
        this.dcs = dcs;
    }
    public String getFlashsms() {
        return flashsms;
    }

    public void setFlashsms(String flashsms) {
        this.flashsms = flashsms;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public static SmsRequest build(String apikey, String senderid, String channel, String dcs,String flashsms, ArrayList<String> numbers,String text,String routeid){
        SmsRequest smsRequest=new SmsRequest();
        smsRequest.setAPIKey(apikey);
        smsRequest.setSenderid(senderid);
        smsRequest.setChannel(channel);
        smsRequest.setDcs(dcs);
        smsRequest.setFlashsms(flashsms);
        smsRequest.setText(text);
        smsRequest.setRouteid(routeid);

        String number="";
        for(int i=0;i<numbers.size();i++){
            if(i!=0){number=number+",";}
            number=number+numbers.get(i);
        }
        smsRequest.setNumber(number);
        return  smsRequest;

    }


}
