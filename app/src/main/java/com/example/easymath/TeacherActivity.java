package com.example.easymath;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easymath.databinding.ActivityTeacherBinding;

import java.util.Calendar;

public class TeacherActivity extends AppCompatActivity {

    TextView tvSelectedDate;
    ActivityTeacherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String sName = intent.getStringExtra("sName");
        String email = intent.getStringExtra("email");

        if (name != null && sName != null && email != null) {

            binding.username.setText(name + " " + sName);
            binding.name.setText(name + " " + sName);
            binding.email.setText(email);
        } else {

            Toast.makeText(TeacherActivity.this, "Қате", Toast.LENGTH_SHORT).show();
        }

        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        Spinner departmentSpinner = findViewById(R.id.departmentSpinner);
        String[] genders = {"Ер", "Әйел"};
        String[] departments = {"Алгебра және геометрия кафедрасы", "Оқу математикасы кафедрасы:", "Информатика және информатика кафедрасы"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(adapter);
        departmentSpinner.setAdapter(adapter1);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String) parent.getItemAtPosition(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showDatePickerDialog(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Обработка выбора даты
                        String birthday = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        tvSelectedDate.setText("Туған күні: " + birthday);
                        Toast.makeText(TeacherActivity.this, "Туған күні: " + birthday, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }

    public void GoToStudents(View v) {
        Intent intent = new Intent(TeacherActivity.this, StudentsActivity.class);
        startActivity(intent);
    }

    public void GoToCourse(View v) {
        Intent intent = new Intent(TeacherActivity.this, Topic1.class);
        startActivity(intent);
    }
}