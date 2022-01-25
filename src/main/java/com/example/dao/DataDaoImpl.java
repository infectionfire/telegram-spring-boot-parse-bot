package com.example.dao;

import com.example.model.Data;
import com.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DataDaoImpl  implements DataDao{

    public Data findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Data.class, id);
    }

    public void save(Data data) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(data);
        tx1.commit();
        session.close();
    }

    public void update(Data data) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(data);
        tx1.commit();
        session.close();
    }

    public void delete(Data data) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(data);
        tx1.commit();
        session.close();
    }
    public List<Data> findAll() {
        List<Data> dataList = (List<Data>)  HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From parse_values").list();
        return dataList;
    }


}
