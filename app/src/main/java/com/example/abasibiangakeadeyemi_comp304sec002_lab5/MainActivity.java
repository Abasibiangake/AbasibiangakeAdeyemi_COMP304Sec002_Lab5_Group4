package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    TextView readData;
    LaptopViewModel laptopViewModel;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mydatabaseRef;
    ArrayList<Laptop> laptopArrayList;
    private FloatingActionButton addNewLaptop;
    private RecyclerView laptopView;
    private ProgressBar loadingSign;
    private RelativeLayout homeLayout;
    private LaptopAdapter laptopAdapter;
    UserViewModel userViewModel;

    public static String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        laptopView = findViewById(R.id.idLaptopView);
        addNewLaptop =findViewById(R.id.idFABAddLaptop);
        firebaseDatabase =FirebaseDatabase.getInstance();
        homeLayout= findViewById(R.id.idDetailSheet);
        loadingSign =findViewById(R.id.idLoadingSign);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);

//        mAuth = FirebaseAuth.getInstance();

        laptopArrayList =new ArrayList<>();
        mydatabaseRef =firebaseDatabase.getReference("LaptopDatabase_Group4");
        laptopAdapter =new LaptopAdapter(this::onLaptopClick, laptopArrayList, this);
        laptopView.setLayoutManager(new LinearLayoutManager(this));
        laptopView.setAdapter(laptopAdapter);

        addNewLaptop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //go to new activity if add button is clicked
                Intent intentAdd = new Intent(MainActivity.this, AddLaptopActivity.class);
                startActivity(intentAdd);
            }
        });
        laptopViewModel = ViewModelProviders.of(this).get(LaptopViewModel.class);
//        Laptop laptop = new Laptop ("Dell", "4GB", "10th Generation Intel Core i3-1005G1","39000", "HP Pavilion 15inch -Intel Pentium",
//                "https://res.cloudinary.com/elite-product/image/upload/v1659548895/electronics/H436edeccbba74d019a1cd6088c0ad88bG_vqv7eu.png");
//        laptopViewModel.insert(laptop);
        getLaptopList();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_icon:
                userViewModel.logout();
                Intent i=new Intent(MainActivity.this,SigninActivity.class);
                startActivity(i);
        }
        return true;
    }
    private void getLaptopList(){
        //clear the laptop list
        laptopArrayList.clear();
        //call the getAlllaptop method from the view model class
        //call child event listener to read all the data in the database
        laptopViewModel.getAllLaptops().addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //hide progressing bar
                loadingSign.setVisibility(View.GONE);
                //read child from the firebase database using snapshot and populate the arrayList
                laptopArrayList.add(snapshot.getValue(Laptop.class));
                //then notify the adapter of the new data is added
                laptopAdapter.notifyDataSetChanged();
            }

            //create method that handles new child added to the database
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //hide progress bar
                loadingSign.setVisibility(View.GONE);
                //notify the adapter
                laptopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // notify adapter when child is removed.
                laptopAdapter.notifyDataSetChanged();
                loadingSign.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // notify adapter when child is moved.
                laptopAdapter.notifyDataSetChanged();
                loadingSign.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onLaptopClick(int i) {
        //this method is called once a laptop item is clicked
        displayLaptopDetail(laptopArrayList.get(i));
    }

    private void displayLaptopDetail(Laptop laptop) {
        //dialog sheet to displaylaptop item detail at the bottom
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        //inflate this layout on the mainactivity
        View detaillayout = LayoutInflater.from(this).inflate(R.layout.laptop_details_layout, homeLayout);
        bottomSheetDialog.setContentView(detaillayout);

        //set cancellable
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);

        //display dialog
        bottomSheetDialog.show();

        //declare and initialise view by id
        TextView laptopName = detaillayout.findViewById(R.id.idLaptopName);
        TextView laptopRAM =detaillayout.findViewById(R.id.idLaptopRAM);
        TextView laptopProcessor =detaillayout.findViewById(R.id.idLaptopProcessor);
        TextView laptopCost =detaillayout.findViewById(R.id.idLaptopCost);
        ImageView laptopImage =detaillayout.findViewById(R.id.idLaptopImage);
        TextView laptopBrand =detaillayout.findViewById(R.id.idLaptopBrand);
        Button editLaptopBtn = detaillayout.findViewById(R.id.idBtnEditDetail);
        Button viewLaptopBtn = detaillayout.findViewById(R.id.idBtnVIewDetails);

        //setvalue of view (textView)
        laptopName.setText(laptop.getLaptopname());
        laptopRAM.setText("RAM: " +laptop.getLaptopRAM());
        laptopProcessor.setText("Processor: "+ laptop.getLaptopprocessor());
        laptopCost.setText("$"+ laptop.getLaptopcost());
        laptopBrand.setText("Brand: "+ laptop.getLaptopbrand());
        Picasso.get().load(laptop.getLaptopImage()).into(laptopImage);

        //listen for when edit laptop detail button is clicked
        editLaptopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEdit = new Intent(MainActivity.this, EditLaptopActivity.class);
                intentEdit.putExtra("detail", laptop);
                startActivity(intentEdit);
            }
        });

        //listen for when delete laptop detail button is clicked
        viewLaptopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentEdit = new Intent(Intent.ACTION_VIEW);
//                intentEdit.setData(Uri, laptop);
//                startActivity(intentEdit);
            }
        });

    }
}
//        readData = findViewById(R.id.textView);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        laptopArrayList = new ArrayList<>();
//
//        // Write a message to the database
////        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = database.getReference("message");
//        laptopViewModel = ViewModelProviders.of(this).get(LaptopViewModel.class);
////        Laptop laptop = new Laptop("Dell", "4GB", "10th Generation Intel Core i3-1005G1", 39000);
////        laptopViewModel.insert(laptop);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("LaptopDB");
//        getAllLaptop();
//
//    }
//
//    private void getAllLaptop() {
//        laptopArrayList.clear();
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                laptopArrayList.add(snapshot.getValue(Laptop.class));
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//}



//        laptopViewModel.getAllLaptops().observe(this, new Observer<HashMap<String, Object>>() {
//            @Override
//            public void onChanged(HashMap<String, Object> laptopHashMap) {
//                StringBuilder output = new StringBuilder();
//                if (laptopHashMap != null) {
//                    for (String laptop : laptopHashMap.keySet()) {
//                        Log.d(TAG, "Key is: " + laptop);
//                        Log.d(TAG, "Value is: " + laptopHashMap.get(laptop));
//                        output.append(laptopHashMap.get(laptop)).append("\n");
//                    }
//                }
//                readData.setText(output.toString());
//            }
//        });



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

