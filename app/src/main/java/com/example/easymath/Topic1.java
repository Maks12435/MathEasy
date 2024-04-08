package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic1);

    }

    public void startTestActivity(View v){
        Intent intent = new Intent(Topic1.this, Test1Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startTopicActivity(View v){
        Intent intent = new Intent(Topic1.this, Topic2.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}