package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic4);
    }

    public void startTest4Activity(View v){
        Intent intent = new Intent(Topic4.this, Test4Activity.class);
        startActivity(intent);
    }

    public void startTopic4Activity(View v){
        Intent intent = new Intent(Topic4.this, Topic5.class);
        startActivity(intent);
    }
}