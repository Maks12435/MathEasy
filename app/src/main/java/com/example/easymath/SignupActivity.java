package com.example.easymath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easymath.databinding.ActivitySignupBinding;
public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                String sName = binding.editTextSName.getText().toString();
                String name = binding.editTextName.getText().toString();
                if(email.equals("")||password.equals("")||sName.equals("")||name.equals(""))
                    Toast.makeText(SignupActivity.this, "Барлық өрістер міндетті болып табылады", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserEmail = databaseHelper.checkEmail(email);
                    if(checkUserEmail == false){
                        Boolean insert = databaseHelper.insertData(name, sName, email, password);
                        if(insert == true){
                            Toast.makeText(SignupActivity.this, "\n" +
                                    "Тіркелу сәтті!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignupActivity.this, "Тіркелу сәтсіз аяқталды!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Пайдаланушы бұрыннан бар! Жүйеге кіріңіз", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}