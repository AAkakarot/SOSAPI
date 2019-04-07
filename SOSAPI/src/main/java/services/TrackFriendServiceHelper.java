package services;

import common.entity.UserPeronalInfo;
import dao.UserPersonalInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrackFriendServiceHelper {
    @Autowired
    UserPersonalInfoDao userPersonalInfoDao;
    @Transactional
    public UserPeronalInfo getUserPersonalInfo(String p){
        return   userPersonalInfoDao.findByPhoneNumber(p);
    }
}
