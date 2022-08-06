//package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class UserDao {
//    private static volatile UserDao INSTANCE;
//
//    private static final String DATABASE_NAME="UserDB";
//    FirebaseDatabase database;
//    DatabaseReference myRef;
//    private FirebaseAuth mAuth;
//    public String TAG="User DAO";
//    private FirebaseUser currentUser;
//    MutableLiveData <Boolean> flagSignup=new MutableLiveData<>();
//    MutableLiveData <Boolean> flagSignin=new MutableLiveData<>();
//
//    private UserDao(){
//        database=FirebaseDatabase.getInstance();
//        myRef= database.getReference(DATABASE_NAME);
//        mAuth=FirebaseAuth.getInstance();
//    }
//    public static UserDao getInstance(){
//        if(INSTANCE==null){
//            INSTANCE=new UserDao();
//        }
//        return INSTANCE;
//    }
//    public void insert (User user){
//        myRef.push().setValue(user);
//    }
//    public FirebaseUser getStatus(){
//        currentUser=mAuth.getCurrentUser();
//        if(currentUser!=null){
//            Log.d(TAG,"CURRENT USER "+currentUser.getDisplayName());
//        }
//        return currentUser;
//    }
//    private void signUpSuccess(boolean signup){flagSignup.postValue(signup);}
//    private void signInSuccess(boolean signin){flagSignin.postValue(signin);}
//
//    public LiveData<Boolean> getSignUpResult(){return flagSignup;}
//    public LiveData<Boolean> getSignInResult(){return flagSignin;}
//
//    public void signUp(String email,String password){
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            currentUser = mAuth.getCurrentUser();
//                            signUpSuccess(true);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                      signUpSuccess(false);
//
//                        }
//                    }
//                });
//    }
//
//    public void login(String email,String password){
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            currentUser = mAuth.getCurrentUser();
//                            signInSuccess(true);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            signInSuccess(false);
//                        }
//                    }
//                });
//    }
//}
