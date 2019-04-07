package dao;
import common.entity.UserPeronalInfo;
import common.entity.UserRegistraionInfo;
import common.entity.VehcilePhoneNumber;
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
public class VehcilePhoneNumberDao extends BaseDaoImpl<VehcilePhoneNumber, String>{
    private static MyLogManager myLogManager =new MyLogManager(UserPersonalInfoDao.class);


    public List<VehcilePhoneNumber> findByPhoneNumber(String phonenumber,String vtype){
        try {
            String hql = "from VehcilePhoneNumber where phonenumber=:phonenumber and vtype=:vtype";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);
            query.setParameter("vtype",vtype);

            List<VehcilePhoneNumber> userPersonalInfoList= query.list();
            return userPersonalInfoList;

        } catch (HibernateException e) {

            myLogManager.error(e);
        }
        return null;
    }

    public int  deleteByPhoneNumber(String phonenumber,String vtype){

            String hql = "delete from VehcilePhoneNumber where phonenumber=:phonenumber and vtype=:vtype";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("phonenumber",phonenumber);
            query.setParameter("vtype",vtype);

            int rowuserPersonalInfoList= query.executeUpdate();
            return rowuserPersonalInfoList;


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
