package com.example.easymath;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.appcompat.app.AppCompatActivity;

public class StudentsActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        dbHelper = new DatabaseHelper(this);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, sName FROM users WHERE email LIKE 'Student%' ORDER BY email", null);
        Cursor cursor2 = db.rawQuery("SELECT correct_answers_count1,  correct_answers_count2, correct_answers_count3, correct_answers_count4, correct_answers_count5 FROM test_results ORDER BY email", null);

        while (cursor.moveToNext() && cursor2.moveToNext()) {
            @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex("name")) + " " +
                    cursor.getString(cursor.getColumnIndex("sName"));

            @SuppressLint("Range") String grade1 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count1"));
            @SuppressLint("Range") String grade2 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count2"));
            @SuppressLint("Range") String grade3 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count3"));
            @SuppressLint("Range") String grade4 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count4"));
            @SuppressLint("Range") String grade5 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count5"));
            @SuppressLint("Range") String grade6 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count6"));
            @SuppressLint("Range") String grade7 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count7"));
            @SuppressLint("Range") String grade8 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count8"));
            @SuppressLint("Range") String grade9 = cursor2.getString(cursor2.getColumnIndex("correct_answers_count9"));

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
            textViewIndex.setText(grade1);
            textViewIndex.setPadding(3, 3, 3, 3);
            textViewIndex.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex2 = new TextView(this);
            textViewIndex2.setLayoutParams(textViewParams);
            textViewIndex2.setText(grade2);
            textViewIndex2.setPadding(3, 3, 3, 3);
            textViewIndex2.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex3 = new TextView(this);
            textViewIndex3.setLayoutParams(textViewParams);
            textViewIndex3.setText(grade3);
            textViewIndex3.setPadding(3, 3, 3, 3);
            textViewIndex3.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex4 = new TextView(this);
            textViewIndex4.setLayoutParams(textViewParams);
            textViewIndex4.setText(grade4);
            textViewIndex4.setPadding(3, 3, 3, 3);
            textViewIndex4.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex5 = new TextView(this);
            textViewIndex5.setLayoutParams(textViewParams);
            textViewIndex5.setText(grade5);
            textViewIndex5.setPadding(3, 3, 3, 3);
            textViewIndex5.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex6 = new TextView(this);
            textViewIndex6.setLayoutParams(textViewParams);
            textViewIndex6.setText(grade6);
            textViewIndex6.setPadding(3, 3, 3, 3);
            textViewIndex6.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex7 = new TextView(this);
            textViewIndex7.setLayoutParams(textViewParams);
            textViewIndex7.setText(grade7);
            textViewIndex7.setPadding(3, 3, 3, 3);
            textViewIndex7.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex8 = new TextView(this);
            textViewIndex8.setLayoutParams(textViewParams);
            textViewIndex8.setText(grade8);
            textViewIndex8.setPadding(3, 3, 3, 3);
            textViewIndex8.setBackgroundResource(R.drawable.table_border);

            TextView textViewIndex9 = new TextView(this);
            textViewIndex9.setLayoutParams(textViewParams);
            textViewIndex9.setText(grade9);
            textViewIndex9.setPadding(3, 3, 3, 3);
            textViewIndex9.setBackgroundResource(R.drawable.table_border);

            textViewName.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f));
            textViewIndex.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex4.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex5.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex6.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex7.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex8.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
            textViewIndex9.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));

            row.addView(textViewName);
            row.addView(textViewIndex);
            row.addView(textViewIndex2);
            row.addView(textViewIndex3);
            row.addView(textViewIndex4);
            row.addView(textViewIndex5);
            row.addView(textViewIndex6);
            row.addView(textViewIndex7);
            row.addView(textViewIndex8);
            row.addView(textViewIndex9);
            tableLayout.addView(row);
        }
        cursor.close();
        cursor2.close();
        db.close();
    }
}