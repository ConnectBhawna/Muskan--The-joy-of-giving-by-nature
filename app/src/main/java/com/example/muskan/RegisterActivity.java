package com.example.muskan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*ActionBar actionbar = getSupportActionBar();
        actionbar.hide();*/
        
        test();
    }
    
    /*
     * This is a Test method to test UI feature functionality.
     * It must be removed during actual feature implementation and should not
     * be considered as actual code for the feature to work.
     */
    private void test ()
    {
        LinearLayout cred = findViewById(R.id.credentials);
        LinearLayout det = findViewById(R.id.personalDetails);
        LinearLayout add = findViewById(R.id.address);
    
        cred.setVisibility(View.VISIBLE);
        det.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
    
        ImageView proceed1 = findViewById(R.id.proceed1);
        proceed1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if(cred.isShown())
                {
                    cred.setVisibility(View.INVISIBLE);
                    det.setVisibility(View.VISIBLE);
                }
            
            }
        });
    
        ImageView proceed2 = findViewById(R.id.proceed2);
        proceed2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if(det.isShown())
                {
                    det.setVisibility(View.INVISIBLE);
                    add.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}