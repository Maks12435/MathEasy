package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminResetActivity extends AppCompatActivity {

    private EditText email1;
    private Button reset;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        databaseHelper = new DatabaseHelper(this);

        email1 = findViewById(R.id.newpsswd);
        reset = findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString().trim();
                Boolean checkCredentials = databaseHelper.checkEmail(email);

                if (checkCredentials) {
                    databaseHelper.resetPassword(email);
                    Toast.makeText(AdminResetActivity.this, "Құпия сөз сәтті қалпына келтірілді", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminResetActivity.this, "Енгізілген электрондық пошта жоқ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void BackToMain(View v) {
        Intent intent = new Intent(AdminResetActivity.this, AdminActivity.class);
        startActivity(intent);
    }
}