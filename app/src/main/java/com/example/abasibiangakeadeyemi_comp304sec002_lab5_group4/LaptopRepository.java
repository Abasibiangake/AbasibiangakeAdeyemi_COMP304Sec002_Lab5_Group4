package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;

public class LaptopRepository {
    private final LaptopDao laptopDao;
    public static LiveData<HashMap<String, Object>> laptopList;


    public LaptopRepository(Context context) {
        //create an interface obejct
        laptopDao = LaptopDao.getInstance();
        //call interface methof
        laptopList = laptopDao.getAllLaptops();
    }

    public void insert(Laptop laptop){
        laptopDao.insert(laptop);
    }

    //return firebase query result as LiveData object
    LiveData<HashMap<String, Object>>getAllLaptops(){
        return laptopList;
    }

}
