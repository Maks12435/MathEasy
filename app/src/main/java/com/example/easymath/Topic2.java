package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic2);
    }

    public void startTest2Activity(View v) {
        Intent intent = new Intent(Topic2.this, Test2Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startTopic2Activity(View v){
        Intent intent = new Intent(Topic2.this, Topic3.class);
        startActivity(intent);
    }
}