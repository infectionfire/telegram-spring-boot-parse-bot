package com.example.service;

import com.example.dao.DataDaoImpl;
import com.example.model.Data;

import java.util.List;

public class DataServiceImpl implements DataService{

    private DataDaoImpl usersDao = new DataDaoImpl();

    public DataServiceImpl() {
    }



    public Data findData(int id) {
        return usersDao.findById(id);
    }

    public void saveData(Data data) {
        usersDao.save(data);
    }

    public void deleteData(Data data) {
        usersDao.delete(data);
    }

    public void updateData(Data data) {
        usersDao.update(data);
    }

    public List<Data> findAllData() {
        return usersDao.findAll();
    }
}
