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

public class Test5Activity extends AppCompatActivity {
    private String[] correctAnswers = {"option1c", "option2d", "option3a", "option4b", "option5a", "option6c", "option7b", "option8d", "option9a", "option10c"};
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

    private boolean testCompleted5 = false;
    private int correctAnswersCount5 = 0;
    private boolean answersChecked5 = false;
    private boolean submitButtonClicked5 = false;
    private TextView correctAnswersCounter5;
    private SharedPreferences sharedPreferences5;

    String email;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test5);

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

        correctAnswersCounter5 = findViewById(R.id.correct_answers_counter);

        sharedPreferences5 = getSharedPreferences("TestState5", Context.MODE_PRIVATE);

        // Load the state if available
        if (savedInstanceState == null) {
            loadTestState();
        } else {
            restoreInstanceState(savedInstanceState);
            submitButtonClicked5 = savedInstanceState.getBoolean("submit_button_clicked", false);
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
                answersChecked5 = true;
                submitButtonClicked5 = true;

                double percentage = (double) correctAnswersCount5 / correctAnswers.length * 100;
                evaluatePerformance(percentage);
            }
        });

        ImageButton nextButton = findViewById(R.id.buttonBack);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked5 = true;
                saveTestState();
                Intent intent = new Intent(Test5Activity.this, Topic5.class);
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
        double percentage = (double) correctAnswersCount5 / correctAnswers.length * 100;
        String percentageText = String.format("Дұрыс жауаптар: %.2f%%", percentage);
        correctAnswersCounter5.setText(percentageText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (submitButtonClicked5) {
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.performClick();
            submitButtonClicked5 = false; // Resetting the flag after performing click action
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTestState(); // Сохраняем состояние теста перед выходом из активити
    }

    private void evaluatePerformance(double percentage) {
        String feedback;
        if (percentage < 50) {
            feedback = "Жақсы емес, материалды қайталаңыз және оралыңыз";
        } else if (percentage >= 50 && percentage < 70) {
            feedback = "Жаман емес, бірақ қателіктеріңізді ескеріңіз";
        } else if (percentage >= 70 && percentage < 90) {
            feedback = "Жақсы, бірақ сәл зейінді болыңыз";
        } else {
            feedback = "Тақырыпты өте жақсы түсіндіңіз, жұмысыңызды жалғастырыңыз";
        }
        // Display feedback using a Toast
        Toast.makeText(this, feedback, Toast.LENGTH_LONG).show();
    }
    private void checkAnswers() {
        correctAnswersCount5 = 0;
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
                    correctAnswersCount5++;
                } else {
                    selectedRadioButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_answer));
                }
                disableRadioGroup(questionRadioGroup);
            }
        }


        databaseHelper.updateTestResult5(email, correctAnswersCount5);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setEnabled(false);
        updateCorrectAnswersCounter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTestState();
        outState.putBoolean("submit_button_clicked", submitButtonClicked5);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        testCompleted5 = savedInstanceState.getBoolean("answers_checked", false);
        correctAnswersCount5 = savedInstanceState.getInt("correct_answers_count", 0);
        answersChecked5 = savedInstanceState.getBoolean("answers_checked", false);
        submitButtonClicked5 = savedInstanceState.getBoolean("submit_button_clicked", false);
        if (testCompleted5) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = savedInstanceState.getInt("selected_radio_button_5" + i);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void loadTestState() {
        testCompleted5 = sharedPreferences5.getBoolean("test_completed5", false);
        correctAnswersCount5 = sharedPreferences5.getInt("correct_answers_count5", 0);
        answersChecked5 = sharedPreferences5.getBoolean("answers_checked5", false);
        submitButtonClicked5 = sharedPreferences5.getBoolean("submit_button_clicked5", false);

        if (testCompleted5) {
            disableAllRadioGroups();
        }

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            int selectedRadioButtonId = sharedPreferences5.getInt("selected_radio_button_5" + i, -1);
            if (selectedRadioButtonId != -1) {
                RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
                questionRadioGroup.check(selectedRadioButtonId);
            }
        }
    }

    private void saveTestState() {
        SharedPreferences.Editor editor = sharedPreferences5.edit();

        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            RadioGroup questionRadioGroup = findViewById(questionRadioGroupIds[i]);
            editor.putInt("selected_radio_button_5" + i, questionRadioGroup.getCheckedRadioButtonId());
        }

        editor.putBoolean("test_completed5", testCompleted5);
        editor.putInt("correct_answers_count5", correctAnswersCount5);
        editor.putBoolean("answers_checked5", answersChecked5);
        editor.putBoolean("submit_button_clicked5", submitButtonClicked5);

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
        correctAnswersCount5 = 0;
        testCompleted5 = false;
        answersChecked5 = false;
        submitButtonClicked5 = false;

        SharedPreferences.Editor editor = sharedPreferences5.edit();
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