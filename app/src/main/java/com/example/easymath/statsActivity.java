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
    String email;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        dbHelper.StartDB(email);

        String email = intent.getStringExtra("email");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT correct_answers_count1, correct_answers_count2,  correct_answers_count3, correct_answers_count4, correct_answers_count5, correct_answers_count6, correct_answers_count7, correct_answers_count8, correct_answers_count9 FROM test_results WHERE email=?", new String[]{email});



        if (cursor != null && cursor.moveToFirst()) {

            BarChartView barChartView = findViewById(R.id.barChartView);

            @SuppressLint("Range") String grade1 = cursor.getString(cursor.getColumnIndex("correct_answers_count1"));
            @SuppressLint("Range") String grade2 = cursor.getString(cursor.getColumnIndex("correct_answers_count2"));
            @SuppressLint("Range") String grade3 = cursor.getString(cursor.getColumnIndex("correct_answers_count3"));
            @SuppressLint("Range") String grade4 = cursor.getString(cursor.getColumnIndex("correct_answers_count4"));
            @SuppressLint("Range") String grade5 = cursor.getString(cursor.getColumnIndex("correct_answers_count5"));
            @SuppressLint("Range") String grade6 = cursor.getString(cursor.getColumnIndex("correct_answers_count6"));
            @SuppressLint("Range") String grade7 = cursor.getString(cursor.getColumnIndex("correct_answers_count7"));
            @SuppressLint("Range") String grade8 = cursor.getString(cursor.getColumnIndex("correct_answers_count8"));
            @SuppressLint("Range") String grade9 = cursor.getString(cursor.getColumnIndex("correct_answers_count9"));

            binding.g1.setText(grade1);
            binding.g2.setText(grade2);
            binding.g3.setText(grade3);
            binding.g4.setText(grade4);
            binding.g5.setText(grade5);
            binding.g6.setText(grade6);
            binding.g7.setText(grade7);
            binding.g8.setText(grade8);
            binding.g9.setText(grade9);

            float[] data = {Float.parseFloat(grade1), Float.parseFloat(grade2), Float.parseFloat(grade3), Float.parseFloat(grade4), Float.parseFloat(grade5), Float.parseFloat(grade6), Float.parseFloat(grade7), Float.parseFloat(grade8), Float.parseFloat(grade9)};
            String[] labels = {"", "", "", "", "", "", "", "", ""};
            barChartView.setData(data, labels);
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