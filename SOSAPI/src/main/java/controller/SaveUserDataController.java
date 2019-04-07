package controller;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.RestoreService;
import services.SaveUserDataService;
import services.SignUpService;
import services.VerifyOtpService;
import util.logger.MyLogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping("/controller/saveuserdata/")
public class SaveUserDataController  {
    @Autowired
    SaveUserDataService saveUserDataService;
    private static MyLogManager myLogManager =new MyLogManager(SaveUserDataController.class);


    /*@RequestMapping(value = "verifyVPA", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse verifyVpa(@RequestBody VerifyVpaRequest verifyVpaRequest) throws ZaakUpiException {
        UpiUtil.validateVpa(verifyVpaRequest.getVpa());
        return RestApiResponse.buildSuccess(upiBaseServiceMtx.verifyVpa(verifyVpaRequest));
    }*/

    @RequestMapping(value = "vehcile", method = RequestMethod.POST)
    @ResponseBody
    public void addVehcile(@RequestParam String phonenumber,String vehcilelist ,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {
        myLogManager.info("inside addvehcile ");
        JSONObject response=saveUserDataService.addVehcile(phonenumber,vehcilelist);
        myLogManager.info(response.toString());
        PrintWriter printWriter=httpServletResponse.getWriter();
        printWriter.print(response.toString());



    }


    @RequestMapping(value = "phone", method = RequestMethod.POST)
    @ResponseBody
    public void addPhone(@RequestParam String phonenumber,String phonelist,String permission ,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {
        myLogManager.info("inside add phone");
        JSONObject response=saveUserDataService.addPhone(phonenumber,phonelist,permission);
        myLogManager.info(response.toString());
        PrintWriter printWriter=httpServletResponse.getWriter();
        printWriter.print(response.toString());



    }

    //Test API
    /*@RequestMapping(value = "insert/transaction", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse insertTransaction(@RequestBody VerifyVpaRequest verifyVpaRequest) throws ZaakUpiException {
        Object data = null;
        try {
            data = upiServiceFactory.getUpiProcessor(UpiServiceProvider.UPI_HDFC)
                    .makeEntryUpiTable();
        } catch (Exception e) {
            logger.error(e, e);
        }
        return RestApiResponse.buildSuccess(data);
    }*/


}
