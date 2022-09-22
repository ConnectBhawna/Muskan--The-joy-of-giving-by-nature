package com.example.muskan;

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

public class SignInActivity extends AppCompatActivity
{
    EditText email,password;
    ImageButton img;
    
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.hide();
    
        test();
        //login details check
        email = findViewById(R.id.loginEmailId);
        password = findViewById(R.id.loginPassword);
        img = findViewById(R.id.loginButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,choiceActivity.class));
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