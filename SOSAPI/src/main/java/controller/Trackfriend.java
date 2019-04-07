package controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.TrackFriendService;
import util.logger.MyLogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class Trackfriend {
    private static MyLogManager myLogManager =new MyLogManager(RestoreController.class);
    @Autowired
    TrackFriendService trackFriendService;
    @RequestMapping(value = "trackfriend", method = RequestMethod.POST)
    @ResponseBody
    public void track(@RequestParam String phonenumber, String data,String trackedno,HttpServletResponse httpServletResponse) throws Exception {
        myLogManager.info("inside verify otp");
        JSONObject response=trackFriendService.sendNotification(phonenumber,data,trackedno);
        myLogManager.info(response.toString());
        PrintWriter printWriter=httpServletResponse.getWriter();
        printWriter.print(response.toString());



    }



}
