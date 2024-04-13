package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Topic3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic3);
    }

    public void startTest3Activity(View v){
        Intent intent = new Intent(Topic3.this, Test3Activity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startTopic3Activity(View v){
        Intent intent = new Intent(Topic3.this, Topic4.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}