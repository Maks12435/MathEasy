package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic7);
    }

    public void startTest7Activity(View v){
        Intent intent = new Intent(Topic7.this, Test7Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }

    public void startTopic7Activity(View v){
        Intent intent = new Intent(Topic7.this, Topic8.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }
}