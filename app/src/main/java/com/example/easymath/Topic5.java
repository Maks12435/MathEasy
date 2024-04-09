package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Topic5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic5);
    }

    public void startTest5Activity(View v){
        Intent intent = new Intent(Topic5.this, Test5Activity.class);
        startActivity(intent);
    }
}