package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easymath.databinding.ActivityProfileContentBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
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
        }
    }

    public void BackToMain(View v) {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}