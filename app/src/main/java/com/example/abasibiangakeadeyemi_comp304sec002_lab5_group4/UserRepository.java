//package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;
//
//import android.content.Context;
//
//import androidx.lifecycle.LiveData;
//
//public class UserRepository {
//
//    private final com.example.abasibiangakeadeyemi_comp304sec002_lab5.UserDao userDao;
//    private LiveData<Boolean> signup;
//    private LiveData<Boolean> signin;
//
//    public UserRepository(Context context){
//        userDao= com.example.abasibiangakeadeyemi_comp304sec002_lab5.UserDao.getInstance();
//        signup=userDao.getSignUpResult();
//        signin=userDao.getSignInResult();
//    }
//
//    public void insert(com.example.abasibiangakeadeyemi_comp304sec002_lab5.User user){
//        userDao.insert(user);
//    }
//
//    public void signUp(String email,String password){
//        userDao.signUp(email,password);
//    }
//    public void login(String email,String password){
//        userDao.login(email,password);
//    }
//
//    public LiveData<Boolean> getSignupResult(){return signup;}
//    public LiveData<Boolean> getSigninResult(){return signin;}
//
//}
