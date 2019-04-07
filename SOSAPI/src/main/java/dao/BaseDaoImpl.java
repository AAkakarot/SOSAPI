package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDaoImpl<T extends Serializable, V extends Serializable> implements BaseDao<T, V> {

    @Autowired
    @Qualifier("sosapi-sessionFactory")
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    public BaseDaoImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    public T save(T entity) throws Exception {
        try {
            return (T) getCurrentSession().save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }


    public void update(T entity) throws Exception {
        try {
            getCurrentSession().update(entity);
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public T findOne(V id) throws Exception {
        try {
            return (T) getCurrentSession().get(entityClass, id);
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public List<T> findAll() throws Exception {
        try {
            return getCurrentSession().createQuery("from " + entityClass.getName()).list();
        } catch (Exception e) {
            throw new Exception();
        }
    }


}
