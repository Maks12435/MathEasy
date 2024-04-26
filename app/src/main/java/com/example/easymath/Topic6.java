package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic6);
    }

    public void startTest6Activity(View v){
        Intent intent = new Intent(Topic6.this, Test6Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }

    public void startTopic6Activity(View v){
        Intent intent = new Intent(Topic6.this, Topic7.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        startActivity(intent);
    }
}