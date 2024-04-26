package com.example.easymath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easymath.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        TextView incorrectCredentialsText = findViewById(R.id.textView25);
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {

                    String role = databaseHelper.getUserRole(email);
                    Intent intent;

                    if ("student".equals(role)) {
                        Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                        if (checkCredentials) {
                            String name = databaseHelper.getUserName(email);
                            String sName = databaseHelper.getUserSurname(email);

                            // Сохранение данных о входе пользователя
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("email", email);
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();

                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("sName", sName);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();

                        } else {
                            incorrectCredentialsText.setVisibility(View.VISIBLE);
                        }

                    } else if ("teacher".equals(role)) {
                        Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                        if (checkCredentials) {
                            String name = databaseHelper.getUserName(email);
                            String sName = databaseHelper.getUserSurname(email);

                            intent = new Intent(LoginActivity.this, TeacherActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("sName", sName);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                        if (checkCredentials) {
                            String name = databaseHelper.getUserName(email);
                            String sName = databaseHelper.getUserSurname(email);

                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("sName", sName);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}