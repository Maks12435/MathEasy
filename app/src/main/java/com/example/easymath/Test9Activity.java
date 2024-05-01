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

public class Test9Activity extends AppCompatActivity {
    private String[] correctAnswers = {"option1c", "option2b", "option3a", "option4d", "option5d", "option6c", "option7b", "option8a", "option9a", "option10c"};
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

    private boolean testCompleted9 = false;
    private int correctAnswersCount9 = 0;
    private boolean answersChecked9 = false;
    private boolean submitButtonClicked9 = false;
    private TextView correctAnswersCounter9;
    private SharedPreferences sharedPreferences9;

    String email;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test9);

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

        correctAnswersCounter9 = findViewById(R.id.correct_answers_counter);

        sharedPreferences9 = getSharedPreferences("TestState_" + email, Context.MODE_PRIVATE);

        // Load the state if available
        if (savedInstanceState == null) {
            loadTestState();
        } else {
            restoreInstanceState(savedInstanceState);
            submitButtonClicked9 = savedInstanceState.getBoolean("submit_button_clicked", false);
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
                answersChecked9 = true;
                submitButtonClicked9 = true;
                double percentage = (double) correctAnswersCount9 / correctAnswers.length * 100;
                evaluatePerformance(percentage);
            }
        });

        ImageButton nextButton = findViewById(R.id.buttonBack);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked9 = true;
                saveTestState();
                Intent intent = new Intent(Test9Activity.this, Topic9.class);
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
        double percentage = (double) correctAnswersCount9 / correctAnswers.length * 100;
        String percentageText = String.format("Дұрыс жауаптар: %.2f%%", percentage);
        correctAnswersCounter9.setText(percentageText);
    }

    private void evaluatePerformance(double percentage) {
        String feedback;
        if (percentage < 50) {
            feedback = "Жақсы емес, материалды қайталаңыз және оралыңыз \uD83D\uDE1E";
        } else if (percentage >= 50 && percentage < 70) {
            feedback = "Жаман емес, бірақ қателіктеріңізді ескеріңіз \uD83D\uDE10";
        } else if (percentage >= 70 && percentage < 90) {
            feedback = "Жақсы, бірақ сәл зейінді болыңыз \uD83D\uDE42";
        } else {
            feedback = "Тақырыпты өте жақсы түсіндіңіз, жұмысыңызды жалғастырыңыз \uD83D\uDE0A";
        }
        // Display feedback using a Toast
        CustomFeedbackDialog.showFeedback(this, feedback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (submitButtonClicked9) {
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.performClick();
            submitButtonClicked9 = false; // Resetting the flag after performing click action
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTestState(); // Сохраняем состояние теста перед выходом из активити
    }
    private void checkAnswers() {
        correctAnswersCount9 = 0;
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
                    correctAnswersCount9++;
                } else {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_answer));
                }
                disableRadioGroup(questionRadioGroup);
            }
        }

        databaseHelper.updateTestResult9(email, correctAnswersCount9);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setEnabled(false);
        updateCorrectAnswersCounter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTestState();
        outState.putBoolean("submit_button_clicked", submitButtonClicked9);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        testCompleted9 = savedInstanceState.getBoolean("answers_checked", false);
        correctAnswersCount9 = savedInstanceState.getInt("correct_answers_count", 0);
        answersChecked9 = savedInstanceState.getBoolean("answers_checked", false);
        submitButtonClicked9 = savedInstanceState.getBoolean("submit_button_clicked", false);
        if (testCompleted9) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = savedInstanceState.getInt("selected_radio_button_9" + i);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void loadTestState() {
        testCompleted9 = sharedPreferences9.getBoolean("test_completed9", false);
        correctAnswersCount9 = sharedPreferences9.getInt("correct_answers_count9", 0);
        answersChecked9 = sharedPreferences9.getBoolean("answers_checked9", false);
        submitButtonClicked9 = sharedPreferences9.getBoolean("submit_button_clicked9", false);

        if (testCompleted9) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = sharedPreferences9.getInt("selected_radio_button_9" + i, -1);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void saveTestState() {
        SharedPreferences.Editor editor = sharedPreferences9.edit();

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            editor.putInt("selected_radio_button_9" + i, questionRadioGroup.getCheckedRadioButtonId());
        }

        editor.putBoolean("test_completed9", testCompleted9);
        editor.putInt("correct_answers_count9", correctAnswersCount9);
        editor.putBoolean("answers_checked9", answersChecked9);
        editor.putBoolean("submit_button_clicked9", submitButtonClicked9);

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
        correctAnswersCount9 = 0;
        testCompleted9 = false;
        answersChecked9 = false;
        submitButtonClicked9 = false;

        SharedPreferences.Editor editor = sharedPreferences9.edit();
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


        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setEnabled(true);
        updateCorrectAnswersCounter();
    }
}
