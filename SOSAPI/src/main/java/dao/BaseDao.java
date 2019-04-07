package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable,V extends Serializable> {
    T save(T entity) throws Exception;
    void update(T entity) throws Exception;
    T findOne(V id) throws Exception;
    List<T> findAll() throws Exception;
}