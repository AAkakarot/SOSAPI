package dao;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import controller.SignUpController;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import util.logger.MyLogManager;

import java.util.*;

@Repository
public class UserPersonalInfoDao extends BaseDaoImpl<UserPeronalInfo, String>{
    private static MyLogManager myLogManager =new MyLogManager(UserPersonalInfoDao.class);


    public UserPeronalInfo findByPhoneNumber(String phonenumber){
        try {
            String hql = "from UserPeronalInfo where phonenumber=:phonenumber";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);

            List<UserPeronalInfo> userPersonalInfoList= query.list();
            if(userPersonalInfoList==null)return null;
            if(userPersonalInfoList.size()==0)return null;
            return userPersonalInfoList.get(0);

        } catch (HibernateException e) {
            myLogManager.error(e);
        }
        return null;
    }

    public void updateVehcile(String phonenumber,String allvehcile) throws Exception{
        try {
            String hql = "update UserPeronalInfo set allvehcile=:allvehcile where phonenumber=:phonenumber ";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);
            query.setParameter("allvehcile",allvehcile);

            int userPersonalInfoList= query.executeUpdate();


        } catch (HibernateException e) {
            myLogManager.error(e);
            throw new Exception();
        }
        return ;
    }

    public void updatephone(String phonenumber,String allphonenumber) throws Exception{
        try {
            String hql = "update UserPeronalInfo set allphonenumber=:allphonenumber where phonenumber=:phonenumber ";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);
            query.setParameter("allphonenumber",allphonenumber);

            int row= query.executeUpdate();


        } catch (HibernateException e) {
            myLogManager.error(e);
            throw new Exception();
        }
        return ;
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
