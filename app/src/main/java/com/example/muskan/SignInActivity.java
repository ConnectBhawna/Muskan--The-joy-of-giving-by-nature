package com.example.muskan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity
{
    EditText email,password;
    ImageButton img;
    ImageView googleSignIn;
    private FirebaseAuth mAuth;
    static GoogleSignInOptions gso;
    static GoogleSignInClient gsc;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.hide();
        mAuth=FirebaseAuth.getInstance();
        test();
        //login details check
        email = findViewById(R.id.loginEmailId);
        password = findViewById(R.id.loginPassword);
        googleSignIn=findViewById(R.id.google_sign);
        img = findViewById(R.id.loginButton);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                        .build() ;
        gsc= GoogleSignIn.getClient(this,gso);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()){
                    login();
                }

                //for admin login
//                if(email.getText().toString().equals("admin123@gmail.com")){
//                    if(!password.getText().toString().equals("admin123")){
//                        Toast.makeText(SignInActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
//                    }else{
//                    startActivity(new Intent(SignInActivity.this ,adminActivity.class));
//                }
//                }
//                else{
//                    //for other users this will direct to firstpage
//                    startActivity(new Intent(SignInActivity.this ,firstPage.class));
//                }
            }
        });
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    googleSign();
            }
        });
    }
    
    /*
     * This is a Test method to test UI feature functionality.
     * It must be removed during actual feature implementation and should not
     * be considered as actual code for the feature to work.
     */
    private void login() {
        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this,choiceActivity.class));


                        } else {
                            Toast.makeText(SignInActivity.this, "Entered details are not correct",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    public  void googleSign(){
        Intent intent=gsc.getSignInIntent();
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                task.getResult(ApiException.class);
                startActivity(new Intent(SignInActivity.this,choiceActivity.class));
            }catch (ApiException e){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean validateData() {


        if (email.getText().toString().isEmpty()) {
            email.setError("Enter E-mail ID");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Enter Password");
            return false;
        }


        return true;
    }
    private void test ()
    {
        TextView reg = findViewById(R.id.directToRegister);
        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                startActivity(new Intent(SignInActivity.this,RegisterActivity.class));
            }
        });
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Google Sign In Success", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                                RegisterActivity.createUserData(user.getEmail(), user.getDisplayName(), new CompleteListener() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(SignInActivity.this,"Sign Up Successfull",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(SignInActivity.this,firstPage.class);
                                        startActivity(intent);
                                        SignInActivity.this.finish();
                                    }

                                    @Override
                                    public void onFailure() {
                                        Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        }
                    }
                });
    }
}