package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<Boolean> signup;
    private LiveData<Boolean> signin;
    public FirebaseUser currentUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository(application);
        signup= userRepository.getSignupResult();
        signin= userRepository.getSigninResult();
        currentUser= userRepository.current();
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public void signUp(String email,String password){userRepository.signUp(email,password);}
    public void login(String email,String password){userRepository.login(email,password);}
    public void logout(){userRepository.logout();}

    public LiveData<Boolean> getSignUpResult(){return signup;}
    public LiveData<Boolean> getSigninResult(){return signin;}
    public FirebaseUser getCurrent(){return currentUser;}
}

