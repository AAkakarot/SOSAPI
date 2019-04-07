package dao;




import common.entity.UserRegistraionInfo;
import controller.SignUpController;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import util.logger.MyLogManager;

import java.util.*;

@Repository
public class SignupDao extends BaseDaoImpl<UserRegistraionInfo, String>{
    private static MyLogManager myLogManager =new MyLogManager(SignupDao.class);


    public UserRegistraionInfo findByPhoneNumber(String phonenumber){
        try {
            String hql = "from UserRegistraionInfo where phonenumber=:phonenumber";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);

            List<UserRegistraionInfo> userRegistraionInfoList= query.list();
            if(userRegistraionInfoList==null)return null;
            if(userRegistraionInfoList.size()==0)return null;
            return userRegistraionInfoList.get(0);

        } catch (HibernateException e) {
            myLogManager.error(e);
        }
        return null;
    }


    public boolean verifyOtp(String phonenumber,String otp){
        try {
            String hql = "from UserRegistraionInfo where phonenumber=:phonenumber and otp=:otp";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);
            query.setParameter("otp",otp);

            List<UserRegistraionInfo> userRegistraionInfoList= query.list();
            if(userRegistraionInfoList==null)return false;
            if(userRegistraionInfoList.size()==0)return false;
            return true;

        } catch (HibernateException e) {
            myLogManager.error(e);
        }
        return false;
    }





   /* public UpiTransaction findByTxnID(String txnId) throws Exception {

        try {
            String hql = "from UpiTransaction where txnId=:txnId";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("txnId",txnId);

            List<UpiTransaction> upiTransactionList= query.list();
            return upiTransactionList.get(0);
        } catch (HibernateException e) {
            throw new Exception(e);
        } catch (Exception e){
            throw new Exception(e);
        }

    }*/
}
