package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class statsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Получите данные от другой активити
        Intent intent = getIntent();
        int correctAnswersCount = intent.getIntExtra("correct_answers_count", 0);
        int totalQuestions = intent.getIntExtra("total_questions", 0);

        // Создайте TextView для отображения статистики
        LinearLayout layout = findViewById(R.id.stats_layout);
        TextView textView = new TextView(this);
        textView.setText("Правильных ответов: " + correctAnswersCount + "/" + totalQuestions);

        // Добавьте TextView в LinearLayout
        layout.addView(textView);
    }
}