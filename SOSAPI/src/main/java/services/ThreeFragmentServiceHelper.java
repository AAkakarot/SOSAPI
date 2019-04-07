package services;

import common.dto.SmsRequest;
import common.entity.AllvehcileCrash;
import common.entity.CrashVehcilePhoneMapping;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import dao.AllCrashDao;
import dao.SignupDao;
import dao.UserPersonalInfoDao;
import dao.VehcilePhoneMappingDao;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.logger.MyLogManager;
import util.network.NetworkUtilHelper;

@Service
public class ThreeFragmentServiceHelper {
    private static MyLogManager myLogManager =new MyLogManager(ThreeFragmentServiceHelper.class);
    @Autowired
    SignupDao signupDao;
    @Autowired
    UserPersonalInfoDao userPersonalInfoDao;
    @Autowired
    NetworkUtilHelper networkUtilHelper;
    @Autowired
    AllCrashDao allCrashDao;
    @Autowired
    VehcilePhoneMappingDao vehcilePhoneMappingDao;


    @Transactional
    void saveAllVehcileCrash(AllvehcileCrash allvehcileCrash) throws Exception{
        allCrashDao.save(allvehcileCrash);
    }
    @Transactional
    void saveAllVehcilePhoneMapping(CrashVehcilePhoneMapping crashVehcilePhoneMapping) throws Exception{
        vehcilePhoneMappingDao.save(crashVehcilePhoneMapping);
    }

    @Transactional
    public UserPeronalInfo getUserPersonalInfo(String p){
      return   userPersonalInfoDao.findByPhoneNumber(p);
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
