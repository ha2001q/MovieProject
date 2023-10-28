package com.example.movieproject.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context) {
        super(context, "userData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Users (\n" +
                "    username VARCHAR(255),\n" +
                "    full_name VARCHAR(255),\n" +
                "    password VARCHAR(255)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Users ");
    }

    public Boolean insertuser(String name,String password,String fullName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",name);
        contentValues.put("full_name",fullName);
        contentValues.put("password",password);
        long result =db.insert("Users",null,contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username", "password"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);

        boolean userExists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return userExists;
    }


}
