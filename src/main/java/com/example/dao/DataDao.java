package com.example.dao;

import com.example.model.Data;

import java.util.List;

public interface DataDao {

    public Data findById(int id) ;
    public void save(Data data) ;
    public void update(Data data) ;
    public void delete(Data data) ;
    public List<Data> findAll() ;
}
