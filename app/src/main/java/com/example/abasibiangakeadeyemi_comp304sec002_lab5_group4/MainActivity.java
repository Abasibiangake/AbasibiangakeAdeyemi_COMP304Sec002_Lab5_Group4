package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView readData;
    LaptopViewModel laptopViewModel;

    public static String TAG = "MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readData = findViewById(R.id.textView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
        laptopViewModel = ViewModelProviders.of(this).get(LaptopViewModel.class);
        Laptop laptop = new Laptop ("Dell", "4GB", "10th Generation Intel Core i3-1005G1", 39000);
        laptopViewModel.insert(laptop);
        laptopViewModel.getAllLaptops().observe(this, new Observer<HashMap<String, Object>>() {
            @Override
            public void onChanged(HashMap<String, Object> laptopHashMap) {
                StringBuilder output = new StringBuilder();
                if (laptopHashMap != null) {
                    for (String laptop : laptopHashMap.keySet()) {
                        Log.d(TAG, "Key is: " + laptop);
                        Log.d(TAG, "Value is: " + laptopHashMap.get(laptop));
                        output.append(laptopHashMap.get(laptop)).append("\n");
                    }
                }
                readData.setText(output.toString());
            }
        });
//        StringBuilder output = new StringBuilder();
//        if (names != null) {
//            for (String person : names.keySet()) {
//                Log.d(TAG, "Key is: " + person);
//                Log.d(TAG, "Value is: " + names.get(person));
//                output.append(names.get(person)).append("\n");
//            }
//        }
//        tv.setText(output.toString());
//




        //myRef.setValue("Hello, World!");

        //HashMap<String, Object> laptopList = (HashMap<String, Object>) dataSnapshot.getValue();
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                readData.setText(value);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }
}