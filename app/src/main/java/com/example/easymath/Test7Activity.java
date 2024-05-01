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

public class Test7Activity extends AppCompatActivity {
    private String[] correctAnswers = {"option1a", "option2b", "option3d", "option4c", "option5b", "option6d", "option7c", "option8a", "option9a", "option10b"};
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

    private boolean testCompleted7 = false;
    private int correctAnswersCount7 = 0;
    private boolean answersChecked7 = false;
    private boolean submitButtonClicked7 = false;
    private TextView correctAnswersCounter7;
    private SharedPreferences sharedPreferences7;

    String email;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test7);

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

        correctAnswersCounter7 = findViewById(R.id.correct_answers_counter);

        sharedPreferences7 = getSharedPreferences("TestState_" + email, Context.MODE_PRIVATE);

        // Load the state if available
        if (savedInstanceState == null) {
            loadTestState();
        } else {
            restoreInstanceState(savedInstanceState);
            submitButtonClicked7 = savedInstanceState.getBoolean("submit_button_clicked", false);
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
                answersChecked7 = true;
                submitButtonClicked7 = true;
                double percentage = (double) correctAnswersCount7 / correctAnswers.length * 100;
                evaluatePerformance(percentage);
            }
        });

        ImageButton nextButton = findViewById(R.id.buttonBack);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked7 = true;
                saveTestState();
                Intent intent = new Intent(Test7Activity.this, Topic7.class);
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
        double percentage = (double) correctAnswersCount7 / correctAnswers.length * 100;
        String percentageText = String.format("Дұрыс жауаптар: %.2f%%", percentage);
        correctAnswersCounter7.setText(percentageText);
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
        if (submitButtonClicked7) {
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.performClick();
            submitButtonClicked7 = false; // Resetting the flag after performing click action
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTestState(); // Сохраняем состояние теста перед выходом из активити
    }

    private void checkAnswers() {
        correctAnswersCount7 = 0;
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
                    correctAnswersCount7++;
                } else {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_answer));
                }
                disableRadioGroup(questionRadioGroup);
            }
        }

        databaseHelper.updateTestResult7(email, correctAnswersCount7);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setEnabled(false);
        updateCorrectAnswersCounter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTestState();
        outState.putBoolean("submit_button_clicked", submitButtonClicked7);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        testCompleted7 = savedInstanceState.getBoolean("answers_checked", false);
        correctAnswersCount7 = savedInstanceState.getInt("correct_answers_count", 0);
        answersChecked7 = savedInstanceState.getBoolean("answers_checked", false);
        submitButtonClicked7 = savedInstanceState.getBoolean("submit_button_clicked", false);
        if (testCompleted7) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = savedInstanceState.getInt("selected_radio_button_7" + i);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void loadTestState() {
        testCompleted7 = sharedPreferences7.getBoolean("test_completed7", false);
        correctAnswersCount7 = sharedPreferences7.getInt("correct_answers_count7", 0);
        answersChecked7 = sharedPreferences7.getBoolean("answers_checked7", false);
        submitButtonClicked7 = sharedPreferences7.getBoolean("submit_button_clicked7", false);

        if (testCompleted7) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = sharedPreferences7.getInt("selected_radio_button_7" + i, -1);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void saveTestState() {
        SharedPreferences.Editor editor = sharedPreferences7.edit();

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            editor.putInt("selected_radio_button_7" + i, questionRadioGroup.getCheckedRadioButtonId());
        }

        editor.putBoolean("test_completed7", testCompleted7);
        editor.putInt("correct_answers_count7", correctAnswersCount7);
        editor.putBoolean("answers_checked7", answersChecked7);
        editor.putBoolean("submit_button_clicked7", submitButtonClicked7);

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
        correctAnswersCount7 = 0;
        testCompleted7 = false;
        answersChecked7 = false;
        submitButtonClicked7 = false;

        SharedPreferences.Editor editor = sharedPreferences7.edit();
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