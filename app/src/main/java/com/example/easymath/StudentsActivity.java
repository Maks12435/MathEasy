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
        Cursor cursor = db.rawQuery("SELECT name, sName FROM users", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex("name")) + " " +
                    cursor.getString(cursor.getColumnIndex("sName"));

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
            textViewIndex.setText(String.valueOf(cursor.getPosition() + 1));
            textViewIndex.setPadding(3, 3, 3, 3);
            textViewIndex.setBackgroundResource(R.drawable.table_border);

            row.addView(textViewName);
            row.addView(textViewIndex);
            tableLayout.addView(row);
        }
        cursor.close();
        db.close();
    }
}