package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class LaptopDao {
    private static volatile LaptopDao INSTANCE;
    private static final String DATABASE_NAME = "LaptopDB";
    FirebaseDatabase database;
    DatabaseReference myLaptopRef;
    public static String TAG = "Laptop DAO";
    public static LiveData<HashMap<String, Object>> laptopList;


    //Singleton Pattern to have one instance of DB

    private LaptopDao(){
        database = FirebaseDatabase.getInstance();
        myLaptopRef = database.getReference(DATABASE_NAME);
    }

    public static LaptopDao getInstance(){
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = new LaptopDao();
        }
        return INSTANCE;
    }

    public void insert(Laptop laptop){
        myLaptopRef.push().setValue(laptop);
    }

    LiveData<HashMap<String, Object>> getAllLaptops(){
        myLaptopRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                laptopList = (LiveData<HashMap<String, Object>>) dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + laptopList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());            }
        });
        return laptopList;
    }
}
