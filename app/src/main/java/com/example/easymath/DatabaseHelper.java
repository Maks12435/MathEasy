package com.example.easymath;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "EN.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "EN.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

        createTableUsers(MyDatabase);
        createTableTestResults(MyDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    private void createTableUsers(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(name TEXT, sName TEXT, email TEXT primary key, password TEXT)");
    }

    private void createTableTestResults(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table test_results(email TEXT primary key, correct_answers_count1 INTEGER DEFAULT 0, correct_answers_count2 INTEGER DEFAULT 0, correct_answers_count3 INTEGER DEFAULT 0, correct_answers_count4 INTEGER DEFAULT 0, correct_answers_count5 INTEGER DEFAULT 0, correct_answers_count6 INTEGER DEFAULT 0, correct_answers_count7 INTEGER DEFAULT 0, correct_answers_count8 INTEGER DEFAULT 0, correct_answers_count9 INTEGER DEFAULT 0)");
    }

    public Boolean insertData(String name, String sName, String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sName", sName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        MyDatabase.close();
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDatabase.close();
        return exists;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDatabase.close();
        return exists;
    }

    public String getUserName(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT name FROM users WHERE email = ?", new String[]{email});
        if(cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return null;
        }
    }

    public String getUserSurname(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT sName FROM users WHERE email = ?", new String[]{email});
        if(cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return null;
        }
    }

    public String getUserRole(String email) {
        String role;
        String prefix = email.substring(0, 7);
        if ("Student".equals(prefix)) {
            role = "student";
        } else if ("Teacher".equals(prefix)) {
            role = "teacher";
        } else {
            role = "unknown";
        }
        return role;
    }

    public void StartDB(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        String result = String.valueOf(MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email}));
        MyDatabase.close();
    }

    public void updateTestResult1(String email, int correctAnswersCount2) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count1", correctAnswersCount2);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
    }

    public void updateTestResult2(String email, int correctAnswersCount2) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count2", correctAnswersCount2);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
    }

    public void updateTestResult3(String email, int correctAnswersCount3) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count3", correctAnswersCount3);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
    }

    public void updateTestResult4(String email, int correctAnswersCount4) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count4", correctAnswersCount4);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
    }

    public Boolean updateTestResult5(String email, int correctAnswersCount5) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count5", correctAnswersCount5);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
        return result != -1;
    }

    public Boolean updateTestResult6(String email, int correctAnswersCount6) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count6", correctAnswersCount6);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
        return result != -1;
    }

    public Boolean updateTestResult7(String email, int correctAnswersCount7) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count7", correctAnswersCount7);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
        return result != -1;
    }

    public Boolean updateTestResult8(String email, int correctAnswersCount8) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count8", correctAnswersCount8);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
        return result != -1;
    }

    public Boolean updateTestResult9(String email, int correctAnswersCount9) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correct_answers_count9", correctAnswersCount9);
        int result = MyDatabase.update("test_results", contentValues, "email = ?", new String[]{email});
        MyDatabase.close();
        return result != -1;
    }
}
