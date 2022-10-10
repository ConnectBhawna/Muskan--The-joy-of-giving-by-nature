package com.example.muskan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity
{
    EditText email,password;
    ImageButton img;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.hide();
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            Intent intent=new Intent(SignInActivity.this,firstPage.class);
            startActivity(intent);
        }
        test();
        //login details check
        email = findViewById(R.id.loginEmailId);
        password = findViewById(R.id.loginPassword);
        img = findViewById(R.id.loginButton);
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
}