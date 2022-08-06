package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditLaptopActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mydatabaseRef;
    LaptopViewModel laptopViewModel;
    Button updateLaptopButton;
    Button delLaptopButton;
    EditText laptopName, laptopRAM, laptopProcessor,
            laptopCost, laptopImageURL, laptopBrand;
    String newlaptopName, newlaptopRAM, newlaptopProcessor,
            newlaptopCost, newlaptopImageURL, newlaptopBrand;
    String laptopKey;
    Laptop laptopList;
    public static String TAG = "EDIT ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_laptop);
        //declare and initialise view by id
        laptopName = findViewById(R.id.idEditLaptopName);
        laptopRAM =findViewById(R.id.idEditLaptopRAM);
        laptopProcessor =findViewById(R.id.idEditLaptopProcessor);
        laptopCost =findViewById(R.id.idEditLaptopCost);
        laptopImageURL =findViewById(R.id.idEditLaptopImage);
        laptopBrand =findViewById(R.id.idEditLaptopBrand);
        updateLaptopButton = findViewById(R.id.idUpdateLaptopButton);
        delLaptopButton = findViewById(R.id.idDeleteLaptopButton);
        laptopViewModel = ViewModelProviders.of(EditLaptopActivity.this).get(LaptopViewModel.class);
        firebaseDatabase = FirebaseDatabase.getInstance();


        //get the passes data from the MainactivityIntent
       laptopList = getIntent().getParcelableExtra("detail");

        if (laptopList != null){
            //get all the laptop detail of the selected
            // laptop into the EditView of the EditLaptopActivity
            laptopName.setText(laptopList.getLaptopname());
            laptopRAM.setText(laptopList.getLaptopRAM());
            laptopProcessor.setText(laptopList.getLaptopprocessor());
            laptopCost.setText(laptopList.getLaptopcost());
            laptopImageURL.setText(laptopList.getLaptopImage());
            laptopBrand.setText(laptopList.getLaptopbrand());
            laptopKey = laptopList.getLaptopkey();

        }
        mydatabaseRef = firebaseDatabase.getReference("LaptopDatabase_Group4").child(laptopKey);

        //when button is pressed call, listener calls this method
        updateLaptopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                newlaptopName = laptopName.getText().toString();
                newlaptopRAM = laptopRAM.getText().toString();
                newlaptopProcessor = laptopProcessor.getText().toString();
                newlaptopCost = laptopCost.getText().toString();
                newlaptopImageURL = laptopImageURL.getText().toString();
                newlaptopBrand = laptopBrand.getText().toString();


                Map<String, Object> laptopHashList = new HashMap<>();
                laptopHashList.put("laptopbrand", newlaptopBrand);
                laptopHashList.put("laptopname", newlaptopName);
                laptopHashList.put("laptopRAM", newlaptopRAM);
                laptopHashList.put("laptopprocessor", newlaptopProcessor);
                laptopHashList.put("laptopcost", newlaptopCost);
                laptopHashList.put("laptopImage", newlaptopImageURL);
                laptopHashList.put("laptopkey", laptopKey);
                Log.d(TAG, "Key is: " + laptopKey);

                laptopViewModel.update(laptopKey, laptopHashList);
                Toast.makeText(EditLaptopActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditLaptopActivity.this, MainActivity.class));

//                laptopViewModel.updateDB().addValueEventListener(new ValueEventListener() {
//
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        mydatabaseRef.updateChildren(laptopHashList);
//                        Toast.makeText(EditLaptopActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//
//                        startActivity(new Intent(EditLaptopActivity.this, MainActivity.class));
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(EditLaptopActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
        });

        //when delete button is pressed call, listener calls this method
        delLaptopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                laptopViewModel.remove(laptopKey);
                startActivity(new Intent(EditLaptopActivity.this, MainActivity.class));

            }
        });


    }

//    public void loadHomePage(View view) {
//        Intent intentHomePage = new Intent(EditLaptopActivity.this, MainActivity.class);
//        startActivity(intentHomePage);
//
//
//    }
}