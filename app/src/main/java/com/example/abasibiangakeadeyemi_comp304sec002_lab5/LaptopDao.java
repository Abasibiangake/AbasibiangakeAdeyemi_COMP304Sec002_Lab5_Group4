package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopDao {
    private static volatile LaptopDao INSTANCE;
    private static final String DATABASE_NAME = "LaptopDatabase_Group4";
    FirebaseDatabase database;
    DatabaseReference myLaptopRef;
    public static String TAG = "Laptop DAO";

    //Singleton Pattern to have one instance of DB
    private LaptopDao() {
        database = FirebaseDatabase.getInstance();
        myLaptopRef = database.getReference(DATABASE_NAME);
    }

    public static LaptopDao getInstance() {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = new LaptopDao();
        }
        return INSTANCE;
    }

    public Task<Void> insert(String key, Laptop laptop) {
        return myLaptopRef.child(key).setValue(laptop);
    }

    public Task<Void> update(String key, Map<String, Object> laptopHashMap) {
        return myLaptopRef.child(key).updateChildren(laptopHashMap);
    }
    public Query updateDB( ) {
        return myLaptopRef;
    }

    public Task<Void> remove(String key) {
        return myLaptopRef.child(key).removeValue();
    }

    public Query get() {
        return myLaptopRef;
    }
}

//    public static String TAG = "Laptop DAO";
//    public static LiveData<HashMap<String, Object>> laptopList;
//
//
//    //Singleton Pattern to have one instance of DB
//
//    private LaptopDao(){
//        database = FirebaseDatabase.getInstance();
//        myLaptopRef = database.getReference(DATABASE_NAME);
//    }
//
//    public static LaptopDao getInstance(){
//        if (INSTANCE == null) {
//            //Create database object
//            INSTANCE = new LaptopDao();
//        }
//        return INSTANCE;
//    }
//
//    public void insert(Laptop laptop){
//        myLaptopRef.push().setValue(laptop);
//    }
//
//    LiveData<HashMap<String, Object>> getAllLaptops(){
//        myLaptopRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                laptopList = (LiveData<HashMap<String, Object>>) dataSnapshot.getValue();
//                Log.d(TAG, "Value is: " + laptopList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                //Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());            }
//        });
//        return laptopList;
//    }

