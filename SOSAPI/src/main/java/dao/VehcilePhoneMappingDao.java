package dao;

import common.entity.AllvehcileCrash;
import common.entity.CrashVehcilePhoneMapping;
import common.entity.UserRegistraionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class VehcilePhoneMappingDao extends BaseDaoImpl<CrashVehcilePhoneMapping, String>{

    boolean saveAll(CrashVehcilePhoneMapping crashVehcilePhoneMapping){
        try{ save(crashVehcilePhoneMapping);}
        catch (Exception e){
            return  false;
        }
        return  true;
    }


}
