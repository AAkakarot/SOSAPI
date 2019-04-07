package services;

import common.dto.SmsRequest;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import common.entity.VehcilePhoneNumber;
import dao.SignupDao;
import dao.UserPersonalInfoDao;
import dao.VehcilePhoneNumberDao;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.logger.MyLogManager;
import util.network.NetworkUtilHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveUserDataServiceHelper {
    private static MyLogManager myLogManager =new MyLogManager(RestoreServiceHelper.class);
    @Autowired
    SignupDao signupDao;
    @Autowired
    UserPersonalInfoDao userPersonalInfoDao;
    @Autowired
    NetworkUtilHelper networkUtilHelper;
    @Autowired
    VehcilePhoneNumberDao vehcilePhoneNumberDao;

@Transactional(rollbackFor = Exception.class)
    public void updateVehciles(String phonenumber,String type,ArrayList<VehcilePhoneNumber> list,String vehcilelist){
    try {
        ;userPersonalInfoDao.updateVehcile(phonenumber,vehcilelist);
        vehcilePhoneNumberDao.deleteByPhoneNumber(phonenumber, type);
        for (int i = 0; i < list.size(); i++) {

            vehcilePhoneNumberDao.save(list.get(i));

        }
    }
    catch (Exception e){
        myLogManager.error(e.toString());
    }
    }



    @Transactional
    public void updatePhone(String phonenumber,String phonelist,String permission){
        try {
            userPersonalInfoDao.updatephone(phonenumber,phonelist);
        } catch (Exception e) {
           myLogManager.error(e);
        }
    }


    @Transactional
    public List<VehcilePhoneNumber> findAllVehcileByType(String phonenumber,String type){
      return   vehcilePhoneNumberDao.findByPhoneNumber(phonenumber,type);
    }
    @Transactional
    public int   deleteAllVehcileByType(String phonenumber,String type){
        return   vehcilePhoneNumberDao.deleteByPhoneNumber(phonenumber,type);
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




}
