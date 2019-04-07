package util.general;

 public interface Stringpool {
     String REQUEST_TIMEOUT="30000";

     interface idType{
         String VEHCILETYPE="1";
         String QRCODETYPE="2";
         String TEMPVEHCILETYPE="3";
     }

     interface smsgateway{
         
        String apikey="Iu1omSJDTk2FJQ5j5N5zeg";
         
        String senderid="WEBSMS";

        String url="https://www.smsgatewayhub.com/api/mt/SendSMS";
        
         interface channel{
             
            String promotional="1";
             
            String transactinal="2";
        }
        
         interface dcs{
             
            String zero="0";
        }
        
         interface flashsms{
            String zero="0";
        }
        
         interface routeid{
            String defaultid="13";
        }
    }
    String firebasekey="key=AAAAW_6YzR0:APA91bELjMXJ9GsARl6Sp7ycl_yk_nF5WxmPCygStQnl1MW-v1UIZ2TjGQFC9pUiNVLpW7ooR_mQqg4pRJBUewBOStKGAqQUSdOn7JYTRMFsQ2webBmiwOvnuYh5mOX1njBjDTbfxuYp";
    String imgdir="/images/";

}
