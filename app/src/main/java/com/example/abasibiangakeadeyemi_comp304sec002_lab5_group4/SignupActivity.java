//package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//
//public class SignupActivity extends AppCompatActivity {
//    UserViewModel userViewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
//    }
//
//    public void signup(View view){
//        EditText etEmail=findViewById(R.id.email_signup);
//        EditText etPassword=findViewById(R.id.password_signup);
//
//        String email=etEmail.getText().toString();
//        String password= etPassword.getText().toString();
//
//        userViewModel.signUp(email,password);
//        userViewModel.getSignUpResult().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean result) {
//                if(result){
//                    Intent i=new Intent(SignupActivity.this,LaptopActivity.class);
//                    startActivity(i);
//                }else{
//                    Toast.makeText(SignupActivity.this,"Sign up error",Toast.LENGTH_LONG);
//                }
//            }
//        });
//    }
//
//    public void goTOLogin(View view){
//        Intent i=new Intent(SignupActivity.this,SigninActivity.class);
//        startActivity(i);
//    }
//}