package com.example.easymath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Test2Activity extends AppCompatActivity {
    private String[] correctAnswers = {"option1b", "option2c", "option3d", "option4a", "option5a", "option6b", "option7d", "option8c", "option9b", "option10a"};
    private int[] questionRadioGroupIds = {R.id.question1Options, R.id.question2Options, R.id.question3Options, R.id.question4Options, R.id.question5Options,
            R.id.question6Options, R.id.question7Options, R.id.question8Options, R.id.question9Options, R.id.question10Options};
    private int[][] radioButtonIds = {
            {R.id.option1a, R.id.option1b, R.id.option1c, R.id.option1d},
            {R.id.option2a, R.id.option2b, R.id.option2c, R.id.option2d},
            {R.id.option3a, R.id.option3b, R.id.option3c, R.id.option3d},
            {R.id.option4a, R.id.option4b, R.id.option4c, R.id.option4d},
            {R.id.option5a, R.id.option5b, R.id.option5c, R.id.option5d},
            {R.id.option6a, R.id.option6b, R.id.option6c, R.id.option6d},
            {R.id.option7a, R.id.option7b, R.id.option7c, R.id.option7d},
            {R.id.option8a, R.id.option8b, R.id.option8c, R.id.option8d},
            {R.id.option9a, R.id.option9b, R.id.option9c, R.id.option9d},
            {R.id.option10a, R.id.option10b, R.id.option10c, R.id.option10d}
    };

    private int[] questionTextViewIds = {R.id.textView1q, R.id.textView2q, R.id.textView3q, R.id.textView4q, R.id.textView5q,
            R.id.textView6q, R.id.textView7q, R.id.textView8q, R.id.textView9q, R.id.textView10q};

    private boolean testCompleted2 = false;
    private int correctAnswersCount2 = 0;
    private boolean answersChecked2 = false;
    private boolean submitButtonClicked2 = false;
    private TextView correctAnswersCounter2;
    private SharedPreferences sharedPreferences2;

    String email;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();;
        email = intent.getStringExtra("email");
        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartTest();
            }
        });


        correctAnswersCounter2 = findViewById(R.id.correct_answers_counter);

        sharedPreferences2 = getSharedPreferences("TestState", Context.MODE_PRIVATE);

        submitButtonClicked2 = sharedPreferences2.getBoolean("submit_button_clicked", false);

        // Проверяем, сохранено ли состояние теста
        if (savedInstanceState == null) {
            loadTestState(); // Загружаем состояние теста при первом запуске активности
        } else {
            restoreInstanceState(savedInstanceState); // Восстанавливаем состояние теста при повороте экрана
            submitButtonClicked2 = savedInstanceState.getBoolean("submit_button_clicked", false);
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
                answersChecked2 = true;
                submitButtonClicked2 = true;
            }
        });

        ImageButton nextButton = findViewById(R.id.buttonBack);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked2 = true;
                saveTestState(); // Сохраняем состояние теста перед переходом на другую активность
                Intent intent = new Intent(Test2Activity.this, Topic2.class);
                startActivity(intent);
                finish(); // Завершаем текущую активность при переходе на следующую
            }
        });

        updateCorrectAnswersCounter();
    }

    private void updateCorrectAnswersCounter() {
        // Вычисляем процент правильных ответов
        double percentage = (double) correctAnswersCount2 / correctAnswers.length * 100;

        // Форматируем строку с процентом правильных ответов
        String percentageText = String.format("Правильных ответов: %.2f%%", percentage);

        // Обновляем текст счетчика правильных ответов
        correctAnswersCounter2.setText(percentageText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Проверяем, была ли кнопка отправки нажата при возвращении на активити
        if (submitButtonClicked2) {
            // Если кнопка отправки была нажата, устанавливаем состояние кнопки отправки в true
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.performClick(); // Программно нажимаем кнопку отправки
            // Сбрасываем флаг submitButtonClicked
            submitButtonClicked2 = false;
        }
    }

    private void checkAnswers() {
        correctAnswersCount2 = 0;
        boolean allQuestionsAnswered = true;

        // Проверяем, были ли отвечены все вопросы
        for (int i = 0; i < correctAnswers.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            int selectedRadioButtonId = questionRadioGroup.getCheckedRadioButtonId();

            // Если хотя бы для одного вопроса не выбран ответ, устанавливаем флаг в false и выходим из цикла
            if (selectedRadioButtonId == -1) {
                allQuestionsAnswered = false;
                break;
            }
        }

        // Если не все вопросы отвечены, выходим из метода
        if (!allQuestionsAnswered) {
            Toast.makeText(this, "Ответьте на все вопросы перед проверкой.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Если все вопросы отвечены, продолжаем с проверкой ответов
        for (int i = 0; i < correctAnswers.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            int selectedRadioButtonId = questionRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            if (selectedRadioButton != null) {
                String selectedOption = getResources().getResourceEntryName(selectedRadioButtonId);

                if (selectedOption.equals(correctAnswers[i])) {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer));
                    correctAnswersCount2++;
                } else {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_answer));
                }
                disableRadioGroup(questionRadioGroup); // Отключить RadioGroup после выбора ответа
            }
        }
        databaseHelper.updateTestResult2(email, correctAnswersCount2);

        updateCorrectAnswersCounter();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState2) {
        super.onSaveInstanceState(outState2);
        saveTestState(); // Сохраняем состояние теста перед поворотом экрана
        outState2.putBoolean("submit_button_clicked", submitButtonClicked2);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        testCompleted2 = savedInstanceState.getBoolean("answers_checked", false);
        correctAnswersCount2 = savedInstanceState.getInt("correct_answers_count", 0);
        answersChecked2 = savedInstanceState.getBoolean("answers_checked", false);
        submitButtonClicked2 = savedInstanceState.getBoolean("submit_button_clicked", false);
        if (testCompleted2) {
            disableAllRadioGroups(); // Если тест уже был пройден, отключите все RadioGroup
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = savedInstanceState.getInt("selected_radio_button_" + i);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void loadTestState() {
        // Загружаем состояние теста из SharedPreferences
        testCompleted2 = sharedPreferences2.getBoolean("test_completed2", false);
        correctAnswersCount2 = sharedPreferences2.getInt("correct_answers_count2", 0);
        answersChecked2 = sharedPreferences2.getBoolean("answers_checked2", false);
        submitButtonClicked2 = sharedPreferences2.getBoolean("submit_button_clicked2", false);

        if (testCompleted2) {
            disableAllRadioGroups(); // Если тест уже был пройден, отключите все RadioGroup
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = sharedPreferences2.getInt("selected_radio_button_2" + i, -1);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void saveTestState() {
        // Сохраняем состояние теста в SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences2.edit();

        // Сохраняем состояние выбранных ответов для каждого вопроса
        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            editor.putInt("selected_radio_button_2" + i, questionRadioGroup.getCheckedRadioButtonId());
        }

        // Сохраняем состояние завершения теста
        editor.putBoolean("test_completed2", testCompleted2);

        // Сохраняем количество правильных ответов
        editor.putInt("correct_answers_count2", correctAnswersCount2);

        // Сохраняем флаг, были ли ответы уже проверены
        editor.putBoolean("answers_checked2", answersChecked2);

        editor.putBoolean("submit_button_clicked2", submitButtonClicked2);

        editor.apply();
    }

    private void disableAllRadioGroups() {
        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            disableRadioGroup(questionRadioGroup);
        }
    }

    private void enableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(true); // Включить все радиокнопки в RadioGroup
        }
    }

    private void disableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false); // Отключить все радиокнопки в RadioGroup
        }
    }

    public void restartTest() {
        // Сбрасываем переменные состояния теста
        correctAnswersCount2 = 0;
        testCompleted2 = false;
        answersChecked2 = false;
        submitButtonClicked2 = false;

        // Очищаем сохраненное состояние теста из SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.clear();
        editor.apply();

        // Сбрасываем отображение выбранных ответов и счетчика правильных ответов
        for (int radioGroupId : questionRadioGroupIds) {
            RadioGroup radioGroup = findViewById(radioGroupId);
            radioGroup.clearCheck();
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                radioGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
            radioGroup.setEnabled(true);
        }

        for (int groupId : questionRadioGroupIds) {
            RadioGroup questionRadioGroup = findViewById(groupId);
            enableRadioGroup(questionRadioGroup);
        }

        // Обновляем счетчик правильных ответов
        updateCorrectAnswersCounter();
    }
}
