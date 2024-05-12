package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class ResetActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private EditText editTextRepeatPassword;
    private Button reset;

    String email;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();;
        email = intent.getStringExtra("email");

        editTextPassword = findViewById(R.id.newpsswd);
        editTextRepeatPassword = findViewById(R.id.newpsswdrepeat);
        reset = findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextPassword.getText().toString().trim();
                String repeatPassword = editTextRepeatPassword.getText().toString().trim();

                if (password.equals(repeatPassword)) {
                    databaseHelper.updatePassword(email, password);
                    Toast.makeText(ResetActivity.this, "Құпия сөз сәтті жаңартылды", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResetActivity.this, "Құпия сөз сәйкес келмеді", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void BackToProfile(View v) {
        Intent intent = new Intent(ResetActivity.this, ProfileActivity.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("sName", getIntent().getStringExtra("sName"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}