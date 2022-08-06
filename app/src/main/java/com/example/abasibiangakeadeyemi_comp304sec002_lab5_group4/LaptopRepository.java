package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import android.content.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import com.google.firebase.database.Query;

public class LaptopRepository {
    private final LaptopDao laptopDao;
    public static Query laptopList;
    public static Query laptopUpdateList;


    public LaptopRepository(Context context) {
        //create an interface obejct
        laptopDao = LaptopDao.getInstance();
        //call interface methof
        laptopList = laptopDao.get();
        laptopUpdateList = laptopDao.updateDB();
    }

    public void insert(String  key, Laptop laptop){
        laptopDao.insert(key, laptop);
    }
    public void update(String  key, Map<String, Object> laptopHashMap){
        laptopDao.update(key, laptopHashMap);
    }

    public Query updateDB(){
        return laptopUpdateList;
    }
    public void remove(String key){
        laptopDao.remove(key);
    }

    public Query getLaptop(){
        return laptopList;
    }

}