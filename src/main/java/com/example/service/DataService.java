package com.example.service;

import com.example.model.Data;

import java.util.List;

public interface DataService {
    public Data findData(int id) ;

    public void saveData(Data data);

    public void deleteData(Data data);

    public void updateData(Data data);

    public List<Data> findAllData();
}
