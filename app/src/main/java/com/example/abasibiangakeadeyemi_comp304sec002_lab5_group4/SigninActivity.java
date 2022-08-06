//package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//
//public class SigninActivity extends AppCompatActivity {
//    UserViewModel userViewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signin);
//        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
//        if(userViewModel.get)
//    }
//
//
//    public void signin(View view) {
//        EditText etEmail=findViewById(R.id.email_signin);
//        EditText etPassword=findViewById(R.id.password_signin);
//
//        String email=etEmail.getText().toString();
//        String password= etPassword.getText().toString();
//
//        userViewModel.login(email,password);
//        userViewModel.getSigninResult().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean result) {
//                if(result){
//                    Intent i=new Intent(SigninActivity.this,LaptopActivity.class);
//                    startActivity(i);
//                }else{
//                    Log.d("","SSSSS");
//                    Toast.makeText(SigninActivity.this,"Sign in error",Toast.LENGTH_LONG);
//                }
//            }
//        });
//    }
//
//    public void goToSignup(View view) {
//        Intent i=new Intent(SigninActivity.this,SignupActivity.class);
//        startActivity(i);
//    }
//}