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
import services.SignUpService;
import services.VerifyOtpService;
import util.logger.MyLogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping("/controller")
public class RestoreController  {
    @Autowired
    RestoreService restoreService;
    private static MyLogManager myLogManager =new MyLogManager(RestoreController.class);


    /*@RequestMapping(value = "verifyVPA", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse verifyVpa(@RequestBody VerifyVpaRequest verifyVpaRequest) throws ZaakUpiException {
        UpiUtil.validateVpa(verifyVpaRequest.getVpa());
        return RestApiResponse.buildSuccess(upiBaseServiceMtx.verifyVpa(verifyVpaRequest));
    }*/

    @RequestMapping(value = "restore", method = RequestMethod.POST)
    @ResponseBody
    public void verifyOtp(@RequestParam String phonenumber, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {
        myLogManager.info("inside verify otp");
        JSONObject response=restoreService.restoreAccount(phonenumber);
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
