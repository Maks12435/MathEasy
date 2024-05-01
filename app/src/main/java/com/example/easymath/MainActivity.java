package com.example.easymath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerLayout;
    NavigationView navigationView;
    private ActionBar actionBar;
    private boolean isNightMode = false;
    String email;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        buttonDrawerLayout = findViewById(R.id.buttonDrawerLayout);
        navigationView = findViewById(R.id.nav_view);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();;
        email = intent.getStringExtra("email");

        databaseHelper.insertTestResult(email);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isNightMode = preferences.getBoolean("isNightMode", false);

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
                    Intent intent = new Intent(MainActivity.this, Topic1.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("sName", getIntent().getStringExtra("sName"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    startActivity(intent);
                } else if (itemId == R.id.nav_tests) {
                    Intent intent = new Intent(MainActivity.this, Test1Activity.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("sName", getIntent().getStringExtra("sName"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    startActivity(intent);
                } else if (itemId == R.id.stat) {
                    Intent intent = new Intent(MainActivity.this, statsActivity.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("sName", getIntent().getStringExtra("sName"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    startActivity(intent);
                } else if (itemId == R.id.theme) {
                    if (isNightMode) {
                        setLightMode();
                    } else {
                        setNightMode();
                    }
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        actionBar = getSupportActionBar();
        updateTheme();
    }

    private void updateTheme() {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            if (actionBar != null) {
                actionBar.setTitle("Night theme: On");
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            if (actionBar != null) {
                actionBar.setTitle("Night theme: Off");
            }
        }
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
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
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

    public void GoToLogin(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void setNightMode() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isNightMode", true);
        editor.apply();

        isNightMode = true;
        updateTheme();
        recreate();
    }

    private void setLightMode() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isNightMode", false);
        editor.apply();

        isNightMode = false;
        updateTheme();
        recreate();
    }

    public void StartTopic1(View v) {
        Intent intent = new Intent(MainActivity.this, Topic1.class);
        startActivity(intent);
    }

    public void StartTopic2(View v) {
        Intent intent = new Intent(MainActivity.this, Topic2.class);
        startActivity(intent);
    }

    public void StartTopic3(View v) {
        Intent intent = new Intent(MainActivity.this, Topic3.class);
        startActivity(intent);
    }

    public void StartTopic4(View v) {
        Intent intent = new Intent(MainActivity.this, Topic4.class);
        startActivity(intent);
    }

    public void StartTopic5(View v) {
        Intent intent = new Intent(MainActivity.this, Topic5.class);
        startActivity(intent);
    }

    public void StartTopic6(View v) {
        Intent intent = new Intent(MainActivity.this, Topic6.class);
        startActivity(intent);
    }

    public void StartTopic7(View v) {
        Intent intent = new Intent(MainActivity.this, Topic7.class);
        startActivity(intent);
    }

    public void StartTopic8(View v) {
        Intent intent = new Intent(MainActivity.this, Topic8.class);
        startActivity(intent);
    }

    public void StartTopic9(View v) {
        Intent intent = new Intent(MainActivity.this, Topic9.class);
        startActivity(intent);
    }
}