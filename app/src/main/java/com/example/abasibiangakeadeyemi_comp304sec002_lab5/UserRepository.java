package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public class UserRepository {

    private final UserDao userDao;
    private LiveData<Boolean> signup;
    private LiveData<Boolean> signin;
    public FirebaseUser currentUser;

    public UserRepository(Context context){
        userDao=UserDao.getInstance();
        signup=userDao.getSignUpResult();
        signin=userDao.getSignInResult();
        currentUser=userDao.getStatus();
    }

    public void insert(User user){
        userDao.insert(user);
    }

    public void signUp(String email,String password){
        userDao.signUp(email,password);
    }
    public void login(String email,String password){
        userDao.login(email,password);
    }
    public void logout(){userDao.logout();}

    public LiveData<Boolean> getSignupResult(){return signup;}
    public LiveData<Boolean> getSigninResult(){return signin;}
    public FirebaseUser current(){return currentUser;}

}
