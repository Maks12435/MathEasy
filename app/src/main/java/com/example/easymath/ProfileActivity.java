package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.easymath.databinding.ActivityProfileContentBinding;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_content);
        drawerLayout = findViewById(R.id.drawer_layout);
        buttonDrawerLayout = findViewById(R.id.buttonDrawerLayout);
        navigationView = findViewById(R.id.nav_view);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name") && intent.hasExtra("sName") && intent.hasExtra("email")) {
            String name = intent.getStringExtra("name");
            String sName = intent.getStringExtra("sName");
            String email = intent.getStringExtra("email");

            if (name != null && sName != null && email != null) {
                binding.username.setText(name + " " + sName);
                binding.name.setText(name + " " + sName);
                binding.email.setText(email);
            } else {
                Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ProfileActivity.this, "No data provided", Toast.LENGTH_SHORT).show();
        }

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
                    Toast.makeText(ProfileActivity.this, "Menu clicked", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.close();

                return false;
            }
        });
    }

    public void startProfileActivity(View v){
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void startCourseActivity(View v){
        Intent intent = new Intent(ProfileActivity.this, Topic1.class);
        startActivity(intent);
    }

    public void BackToMain(View v) {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}