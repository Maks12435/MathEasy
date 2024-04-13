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

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT correct_answers_count1, correct_answers_count2,  correct_answers_count3, correct_answers_count4, correct_answers_count5 FROM test_results WHERE email=?", new String[]{email});



        if (cursor != null && cursor.moveToFirst()) {

            BarChartView barChartView = findViewById(R.id.barChartView);

            @SuppressLint("Range") String grade1 = cursor.getString(cursor.getColumnIndex("correct_answers_count1"));
            @SuppressLint("Range") String grade2 = cursor.getString(cursor.getColumnIndex("correct_answers_count2"));
            @SuppressLint("Range") String grade3 = cursor.getString(cursor.getColumnIndex("correct_answers_count3"));
            @SuppressLint("Range") String grade4 = cursor.getString(cursor.getColumnIndex("correct_answers_count4"));
            @SuppressLint("Range") String grade5 = cursor.getString(cursor.getColumnIndex("correct_answers_count5"));

            binding.g1.setText(grade1);
            binding.g2.setText(grade2);
            binding.g3.setText(grade3);
            binding.g4.setText(grade4);
            binding.g5.setText(grade5);

            float total = (Float.parseFloat(grade1) + Float.parseFloat(grade2) + Float.parseFloat(grade3) + Float.parseFloat(grade4) + Float.parseFloat(grade5))/5;

            float[] data = {Float.parseFloat(grade1), Float.parseFloat(grade2), Float.parseFloat(grade3), Float.parseFloat(grade4), Float.parseFloat(grade5), total};
            String[] labels = {"", "", "", "", "", ""};
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