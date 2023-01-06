package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopViewModel extends AndroidViewModel {
    private LaptopRepository laptopRepository;
    private Query allLaptops;
    private Query allLaptopUpdate;

    public LaptopViewModel(@NonNull Application application) {
        super(application);
        laptopRepository = new LaptopRepository(application);
        allLaptops = laptopRepository.getLaptop();
        allLaptopUpdate = laptopRepository.updateDB();
    }

    public void insert(String  key, Laptop laptop){
        laptopRepository.insert(key, laptop);
    }

    public void update(String key, Map<String, Object> laptopHashMap){
        laptopRepository.update(key, laptopHashMap);
    }

    public Query updateDB(){
        return allLaptopUpdate;
    }

    public void remove(String key){
        laptopRepository.remove(key);
    }

    public Query getAllLaptops(){
        return allLaptops;
    }
}
