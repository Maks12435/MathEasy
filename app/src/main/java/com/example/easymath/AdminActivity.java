package com.example.easymath;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        dbHelper = new DatabaseHelper(this);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, sName, email, password FROM users WHERE email NOT LIKE 'T%' ORDER BY email", null);
        Cursor cursor2 = db.rawQuery("SELECT name, sName, email, password FROM users WHERE email LIKE 'T%' ORDER BY email", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex("name")) + " " +
                    cursor.getString(cursor.getColumnIndex("sName"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));

            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutParams);

            TextView textViewName = new TextView(this);
            TableRow.LayoutParams textViewParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            textViewName.setLayoutParams(textViewParams);
            textViewName.setText(fullName);
            textViewName.setPadding(3, 3, 3, 3);
            textViewName.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex = new TextView(this);
            textViewIndex.setLayoutParams(textViewParams);
            textViewIndex.setText(email);
            textViewIndex.setPadding(3, 3, 3, 3);
            textViewIndex.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex2 = new TextView(this);
            textViewIndex2.setLayoutParams(textViewParams);
            textViewIndex2.setText(password);
            textViewIndex2.setPadding(3, 3, 3, 3);
            textViewIndex2.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex3 = new TextView(this);
            textViewIndex3.setLayoutParams(textViewParams);
            textViewIndex3.setText("student");
            textViewIndex3.setPadding(3, 3, 3, 3);
            textViewIndex3.setBackgroundResource(R.drawable.table_border);

            textViewName.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));

            row.addView(textViewName);
            row.addView(textViewIndex);
            row.addView(textViewIndex2);
            row.addView(textViewIndex3);
            tableLayout.addView(row);
        }

        while (cursor2.moveToNext()) {
            @SuppressLint("Range") String fullNamet = cursor2.getString(cursor2.getColumnIndex("name")) + " " +
                    cursor2.getString(cursor2.getColumnIndex("sName"));
            @SuppressLint("Range") String emailt = cursor2.getString(cursor2.getColumnIndex("email"));
            @SuppressLint("Range") String passwordt = cursor2.getString(cursor2.getColumnIndex("password"));

            TableRow row20 = new TableRow(this);
            TableRow.LayoutParams layoutParams20 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row20.setLayoutParams(layoutParams20);

            TextView textViewName20 = new TextView(this);
            TableRow.LayoutParams textViewParams20 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            textViewName20.setLayoutParams(textViewParams20);
            textViewName20.setText(fullNamet);
            textViewName20.setPadding(3, 3, 3, 3);
            textViewName20.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex20 = new TextView(this);
            textViewIndex20.setLayoutParams(textViewParams20);
            textViewIndex20.setText(emailt);
            textViewIndex20.setPadding(3, 3, 3, 3);
            textViewIndex20.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex21 = new TextView(this);
            textViewIndex21.setLayoutParams(textViewParams20);
            textViewIndex21.setText(passwordt);
            textViewIndex21.setPadding(3, 3, 3, 3);
            textViewIndex21.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex22 = new TextView(this);
            textViewIndex22.setLayoutParams(textViewParams20);
            textViewIndex22.setText("teacher");
            textViewIndex22.setPadding(3, 3, 3, 3);
            textViewIndex22.setBackgroundResource(R.drawable.table_border);

            textViewName20.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex20.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex21.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex22.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));

            row20.addView(textViewName20);
            row20.addView(textViewIndex20);
            row20.addView(textViewIndex21);
            row20.addView(textViewIndex22);
            tableLayout.addView(row20);
        }
        cursor.close();
        cursor2.close();
        db.close();
    }

    public void startReset(View v) {
        Intent intent = new Intent(AdminActivity.this, AdminResetActivity.class);
        startActivity(intent);
    }
}