package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic9);
    }

    public void startTest9Activity(View v){
        Intent intent = new Intent(Topic9.this, Test9Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }

    public void startTopic9Activity(View v){
        Intent intent = new Intent(Topic9.this, MainActivity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }
}