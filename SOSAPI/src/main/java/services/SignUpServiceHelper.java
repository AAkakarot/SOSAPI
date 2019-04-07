package services;

import common.dto.SmsRequest;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import dao.SignupDao;
import dao.UserPersonalInfoDao;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.logger.MyLogManager;
import util.network.NetworkUtilHelper;

@Service
public class SignUpServiceHelper {
    private static MyLogManager myLogManager =new MyLogManager(SignUpServiceHelper.class);
    @Autowired
    SignupDao signupDao;
    @Autowired
    UserPersonalInfoDao userPersonalInfoDao;
    @Autowired
    NetworkUtilHelper networkUtilHelper;


    @Transactional
    public UserRegistraionInfo isAlreadyRegister(String phonenumber){
       UserRegistraionInfo userRegistraionInfo= signupDao.findByPhoneNumber(phonenumber);
       return userRegistraionInfo;
    }

    @Transactional
    public void  updateRegistration(UserRegistraionInfo userRegistraionInfo) {
        try {
            signupDao.update(userRegistraionInfo);
        }
        catch (Exception e){
            myLogManager.error(e);
        }
    }

    @Transactional
    public void  saveRegistration(UserRegistraionInfo userRegistraionInfo) {
        try {
            signupDao.save(userRegistraionInfo);
        }
        catch (Exception e){
            myLogManager.error(e);
        }
    }

    @Transactional
    public void savePersonalInfo(UserPeronalInfo userPeronalInfo){
        try {
            userPersonalInfoDao.save(userPeronalInfo);
        }
        catch (Exception e){
            myLogManager.error(e);
        }
    }

    @Transactional
    public void updatePersonalInfo(UserPeronalInfo userPeronalInfo){
        try {
            userPersonalInfoDao.update(userPeronalInfo);
        }
        catch (Exception e){
            myLogManager.error(e);
        }
    }

    public String sendOtp(SmsRequest smsRequest){
        return networkUtilHelper.sendSms(smsRequest);
    }


}
