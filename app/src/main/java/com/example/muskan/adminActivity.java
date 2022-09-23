package com.example.muskan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class adminActivity extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout layout;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initViews();
        //navigation menu items click
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(adminActivity.this,layout,toolbar,R.string.nav_open,R.string.nav_close);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        nav.bringToFront();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        Toast.makeText(adminActivity.this,"Profile ",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.setting:
                        Toast.makeText(adminActivity.this,"Setting ",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.signOut:
                        Toast.makeText(adminActivity.this,"SignOut ",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(adminActivity.this ,SignInActivity.class));

                        break;
                    case R.id.about:
                        Toast.makeText(adminActivity.this,"About us ",Toast.LENGTH_SHORT).show();
                        break;
                    default :
                        return true;
                }
                return true;
            }
        });


    }

    private void initViews() {

        nav = findViewById(R.id.adminNav);
        layout = findViewById(R.id.adminDrawerLayout);
        toolbar=findViewById(R.id.adminToolbar);

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