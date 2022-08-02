package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;

public class LaptopViewModel extends AndroidViewModel {
    private LaptopRepository laptopRepository;
    private LiveData<HashMap<String, Object>> allLaptops;
    public LaptopViewModel(@NonNull Application application) {
        super(application);
        laptopRepository = new LaptopRepository(application);
        allLaptops = laptopRepository.getAllLaptops();
    }

    public void insert(Laptop laptop){
        laptopRepository.insert(laptop);
    }
    public LiveData<HashMap<String, Object>>getAllLaptops(){return allLaptops;}
}
