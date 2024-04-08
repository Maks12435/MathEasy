package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic3);
    }

    public void startTest3Activity(View v){
        Intent intent = new Intent(Topic3.this, Test3Activity.class);
        startActivity(intent);
    }

    public void startTopic3Activity(View v){
        Intent intent = new Intent(Topic3.this, Topic4.class);
        startActivity(intent);
    }
}