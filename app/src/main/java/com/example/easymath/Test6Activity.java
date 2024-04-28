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

public class Test6Activity extends AppCompatActivity {
    private String[] correctAnswers = {"option1b", "option2b", "option3a", "option4d", "option5a", "option6b", "option7a", "option8c", "option9d", "option10b"};
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

    private boolean testCompleted6 = false;
    private int correctAnswersCount6 = 0;
    private boolean answersChecked6 = false;
    private boolean submitButtonClicked6 = false;
    private TextView correctAnswersCounter6;
    private SharedPreferences sharedPreferences6;

    String email;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test6);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartTest();
            }
        });

        correctAnswersCounter6 = findViewById(R.id.correct_answers_counter);

        sharedPreferences6 = getSharedPreferences("TestState6", Context.MODE_PRIVATE);

        // Load the state if available
        if (savedInstanceState == null) {
            loadTestState();
        } else {
            restoreInstanceState(savedInstanceState);
            submitButtonClicked6 = savedInstanceState.getBoolean("submit_button_clicked", false);
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
                answersChecked6 = true;
                submitButtonClicked6 = true;
            }
        });

        ImageButton nextButton = findViewById(R.id.buttonBack);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked6 = true;
                saveTestState();
                Intent intent = new Intent(Test6Activity.this, Topic6.class);
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("sName", getIntent().getStringExtra("sName"));
                startActivity(intent);
                finish();
            }
        });

        updateCorrectAnswersCounter();
    }

    private void updateCorrectAnswersCounter() {
        double percentage = (double) correctAnswersCount6 / correctAnswers.length * 100;
        String percentageText = String.format("Дұрыс жауаптар: %.2f%%", percentage);
        correctAnswersCounter6.setText(percentageText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (submitButtonClicked6) {
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.performClick();
            submitButtonClicked6 = false; // Resetting the flag after performing click action
        }
    }

    private void checkAnswers() {
        correctAnswersCount6 = 0;
        boolean allQuestionsAnswered = true;

        for (int i = 0; i < correctAnswers.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            int selectedRadioButtonId = questionRadioGroup.getCheckedRadioButtonId();

            if (selectedRadioButtonId == -1) {
                allQuestionsAnswered = false;
                break;
            }
        }

        if (!allQuestionsAnswered) {
            Toast.makeText(this, "Тексермес бұрын барлық сұрақтарға жауап беріңіз.", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < correctAnswers.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            int selectedRadioButtonId = questionRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            if (selectedRadioButton != null) {
                String selectedOption = getResources().getResourceEntryName(selectedRadioButtonId);

                if (selectedOption.equals(correctAnswers[i])) {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer));
                    correctAnswersCount6++;
                } else {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_answer));
                }
                disableRadioGroup(questionRadioGroup);
            }
        }

        databaseHelper.updateTestResult6(email, correctAnswersCount6);
        updateCorrectAnswersCounter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTestState();
        outState.putBoolean("submit_button_clicked", submitButtonClicked6);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        testCompleted6 = savedInstanceState.getBoolean("answers_checked", false);
        correctAnswersCount6 = savedInstanceState.getInt("correct_answers_count", 0);
        answersChecked6 = savedInstanceState.getBoolean("answers_checked", false);
        submitButtonClicked6 = savedInstanceState.getBoolean("submit_button_clicked", false);
        if (testCompleted6) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = savedInstanceState.getInt("selected_radio_button_6" + i);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void loadTestState() {
        testCompleted6 = sharedPreferences6.getBoolean("test_completed6", false);
        correctAnswersCount6 = sharedPreferences6.getInt("correct_answers_count6", 0);
        answersChecked6 = sharedPreferences6.getBoolean("answers_checked6", false);
        submitButtonClicked6 = sharedPreferences6.getBoolean("submit_button_clicked6", false);

        if (testCompleted6) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = sharedPreferences6.getInt("selected_radio_button_6" + i, -1);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void saveTestState() {
        SharedPreferences.Editor editor = sharedPreferences6.edit();

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            editor.putInt("selected_radio_button_6" + i, questionRadioGroup.getCheckedRadioButtonId());
        }

        editor.putBoolean("test_completed6", testCompleted6);
        editor.putInt("correct_answers_count6", correctAnswersCount6);
        editor.putBoolean("answers_checked6", answersChecked6);
        editor.putBoolean("submit_button_clicked6", submitButtonClicked6);

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
            radioGroup.getChildAt(i).setEnabled(true);
        }
    }

    private void disableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void restartTest() {
        correctAnswersCount6 = 0;
        testCompleted6 = false;
        answersChecked6 = false;
        submitButtonClicked6 = false;

        SharedPreferences.Editor editor = sharedPreferences6.edit();
        editor.clear();
        editor.apply();

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

        updateCorrectAnswersCounter();
    }
}