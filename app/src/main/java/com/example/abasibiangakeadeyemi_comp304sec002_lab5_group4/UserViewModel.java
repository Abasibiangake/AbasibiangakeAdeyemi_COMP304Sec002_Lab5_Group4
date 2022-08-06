//package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//public class UserViewModel extends AndroidViewModel {
//    private UserRepository userRepository;
//    private LiveData<Boolean> signup;
//    private LiveData<Boolean> signin;
//
//    public UserViewModel(@NonNull Application application) {
//        super(application);
//        userRepository=new UserRepository(application);
//        signup= userRepository.getSignupResult();
//        signin= userRepository.getSigninResult();
//    }
//
//    public void insert(com.example.abasibiangakeadeyemi_comp304sec002_lab5.User user){
//        userRepository.insert(user);
//    }
//
//    public void signUp(String email,String password){userRepository.signUp(email,password);}
//    public void login(String email,String password){userRepository.login(email,password);}
//
//    public LiveData<Boolean> getSignUpResult(){return signup;}
//    public LiveData<Boolean> getSigninResult(){return signin;}
//}
//
