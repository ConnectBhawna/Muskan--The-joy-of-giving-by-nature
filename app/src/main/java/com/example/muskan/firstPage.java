package com.example.muskan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class firstPage extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout layout;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        initViews();
        //navigation menu items click
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(firstPage.this,layout,toolbar,R.string.nav_open,R.string.nav_close);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        nav.bringToFront();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        Toast.makeText(firstPage.this,"Profile ",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.setting:
                        Toast.makeText(firstPage.this,"Setting ",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.signOut:
                        startActivity(new Intent(firstPage.this ,SignInActivity.class));
                        Toast.makeText(firstPage.this,"SignOut ",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.about:
                        Toast.makeText(firstPage.this,"About us ",Toast.LENGTH_SHORT).show();
                        break;
                    default :
                        return true;
                }
                return true;
            }
        });


    }

    private void initViews() {

        nav = findViewById(R.id.nav);
        layout = findViewById(R.id.my_drawer_layout);
        toolbar=findViewById(R.id.toolbar);

    }
    @Override  //Back button closes drawer
    public void onBackPressed() {
        if(layout.isDrawerOpen((GravityCompat.START)))
        {
            layout.closeDrawer(GravityCompat.START);
        }
//        else{
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
    }


}