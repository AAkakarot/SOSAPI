package util.general;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class SosMath {
    Random random=new Random();
    String number="0123456789";
    public
    String getRandomString(int len){
        String str="";
        for(int i=0;i<len;i++){
            str=str+number.charAt(random.nextInt(number.length()));
        }
        return str;
    }
}
