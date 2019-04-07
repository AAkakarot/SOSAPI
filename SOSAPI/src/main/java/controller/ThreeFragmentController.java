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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.RestoreService;
import services.SignUpService;
import services.ThreeFragmentService;
import services.VerifyOtpService;
import util.logger.MyLogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping("/controller")
public class ThreeFragmentController  {
    @Autowired
    ThreeFragmentService threeFragmentService;
    private static MyLogManager myLogManager =new MyLogManager(ThreeFragmentController.class);


    /*@RequestMapping(value = "verifyVPA", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse verifyVpa(@RequestBody VerifyVpaRequest verifyVpaRequest) throws ZaakUpiException {
        UpiUtil.validateVpa(verifyVpaRequest.getVpa());
        return RestApiResponse.buildSuccess(upiBaseServiceMtx.verifyVpa(verifyVpaRequest));
    }*/

    @RequestMapping(value="/threefragment", method=RequestMethod.POST,consumes = {"multipart/form-data","application/x-www-form-urlencoded","text/plain"})
    @ResponseBody
    public void sendSos(@RequestParam("phonenumber") String phonenumber, @RequestParam  String data, @RequestParam String vehcile, @RequestParam("image") MultipartFile image , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        myLogManager.info("inside send sos");
        JSONObject response=threeFragmentService.uploadFileAndSendSos(phonenumber,data , image);
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
