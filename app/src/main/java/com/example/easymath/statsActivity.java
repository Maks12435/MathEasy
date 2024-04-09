package com.example.easymath;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easymath.databinding.ActivityStatsBinding;

public class statsActivity extends AppCompatActivity {

    ActivityStatsBinding binding;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT correct_answers_count1, correct_answers_count2 FROM test_results", null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String grade1 = cursor.getString(cursor.getColumnIndex("correct_answers_count1"));
            @SuppressLint("Range") String grade2 = cursor.getString(cursor.getColumnIndex("correct_answers_count2"));

            binding.g1.setText(grade1);
            binding.g2.setText(grade2);

            cursor.close();
        }
    }

    public void BackToMainStats(View v) {
        Intent intent = new Intent(statsActivity.this, MainActivity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startTopicActivityStats(View v) {
        Intent intent = new Intent(statsActivity.this, Topic1.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void startProfileActivityStats(View v) {
        Intent intent = new Intent(statsActivity.this, ProfileActivity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}