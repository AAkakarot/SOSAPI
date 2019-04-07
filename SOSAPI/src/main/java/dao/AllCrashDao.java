package dao;

import common.entity.AllvehcileCrash;
import common.entity.UserRegistraionInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AllCrashDao extends BaseDaoImpl<AllvehcileCrash, String> {

    boolean saveAll(AllvehcileCrash allvehcileCrash){
       try{ save(allvehcileCrash);}
       catch (Exception e){
           return  false;
       }
       return  true;
    }
}
