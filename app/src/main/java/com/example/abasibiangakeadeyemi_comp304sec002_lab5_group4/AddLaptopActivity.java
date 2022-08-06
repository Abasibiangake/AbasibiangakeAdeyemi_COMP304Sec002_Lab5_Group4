package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddLaptopActivity extends AppCompatActivity {
    LaptopViewModel laptopViewModel;
    Button addLaptopButton;
    EditText laptopName, laptopRAM, laptopProcessor,
            laptopCost, laptopImageURL, laptopBrand;
    String newlaptopName, newlaptopRAM, newlaptopProcessor,
            newlaptopCost, newlaptopImageURL, newlaptopBrand;
    String laptopKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);

        //declare and initialise view by id
        laptopName = findViewById(R.id.idEditLaptopName);
        laptopRAM =findViewById(R.id.idEditLaptopRAM);
        laptopProcessor =findViewById(R.id.idEditLaptopProcessor);
        laptopCost =findViewById(R.id.idEditLaptopCost);
        laptopImageURL =findViewById(R.id.idEditLaptopImage);
        laptopBrand =findViewById(R.id.idEditLaptopBrand);
        addLaptopButton = findViewById(R.id.idAddLaptopButton);

        //when button is pressed call, listener calls this method
        addLaptopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newlaptopName = laptopName.getText().toString();
                newlaptopRAM = laptopRAM.getText().toString();
                newlaptopProcessor = laptopProcessor.getText().toString();
                newlaptopCost = laptopCost.getText().toString();
                newlaptopImageURL = laptopImageURL.getText().toString();
                newlaptopBrand = laptopBrand.getText().toString();
                //replace autogeneration of unique keys
                laptopKey = newlaptopName;  //laptopName.getText().toString();
                laptopViewModel = ViewModelProviders.of(AddLaptopActivity.this).get(LaptopViewModel.class);
                Laptop laptop = new Laptop ( newlaptopBrand,
                        newlaptopRAM,
                        newlaptopProcessor,
                        newlaptopCost,
                        newlaptopName,
                        newlaptopImageURL,
                        laptopKey);
                laptopViewModel.insert(laptopKey, laptop);
                startActivity(new Intent(AddLaptopActivity.this, MainActivity.class));

            }
        });

    }

    public void loadHomePage(View view) {
        Intent intentHomePage = new Intent(AddLaptopActivity.this, MainActivity.class);
        startActivity(intentHomePage);
    }
}