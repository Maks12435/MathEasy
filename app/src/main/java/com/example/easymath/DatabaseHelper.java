package com.example.easymath;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Sign.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Sign.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        createTableUsers(MyDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    private void createTableUsers(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(name TEXT, sName TEXT, email TEXT primary key, password TEXT)");
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
        String prefix = email.substring(0, 2);
        if ("22".equals(prefix)) {
            role = "student";
        } else if ("11".equals(prefix)) {
            role = "teacher";
        } else {
            role = "unknown";
        }
        return role;
    }
}
