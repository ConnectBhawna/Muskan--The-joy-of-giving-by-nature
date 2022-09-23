package com.example.muskan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class choiceActivity extends AppCompatActivity {
    MaterialButton admin,user;
    EditText pass;
    LinearLayout ll;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        admin = findViewById(R.id.choiceadmin);
        user = findViewById(R.id.choiceuser);
        pass = findViewById(R.id.choicepassword);
        ll = findViewById(R.id.choiceadminLL);
        ok = findViewById(R.id.ok);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choiceActivity.this,firstPage.class));
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setVisibility(View.VISIBLE);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals("admin123")){
                    startActivity(new Intent(choiceActivity.this,adminActivity.class));
                }else {
                    Toast.makeText(choiceActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}