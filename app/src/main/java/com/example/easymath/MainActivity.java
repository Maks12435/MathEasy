package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        buttonDrawerLayout = findViewById(R.id.buttonDrawerLayout);
        navigationView = findViewById(R.id.nav_view);

        buttonDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_lessons) {
                    setNightMode();
                    return true;
                }

                else if (itemId == R.id.nav_tests) {
                    setLightMode();
                    return true;
                }
                drawerLayout.close();

                return false;
            }
        });
    }

    public void startProfileActivity(View v) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startCourseActivity(View v){
        Intent intent = new Intent(MainActivity.this, Topic1.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startStats(View v) {
        Intent intent = new Intent(MainActivity.this, statsActivity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    private void setNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        recreate();
    }

    private void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }
}