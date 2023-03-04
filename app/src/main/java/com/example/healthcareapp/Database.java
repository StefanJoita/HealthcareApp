package com.example.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry1= "create table users(username text,email text, password text)";
        sqLiteDatabase.execSQL(qry1);

    }
    public void onUpgrade(SQLiteDatabase sqlLiteDatabase, int i, int i1)
    {

    }
    public void register(String username,String email, String password)
    {
        ContentValues cv= new ContentValues();
        cv.put("username",username);
        cv.put("email",password);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public boolean login(String username,String password)
    {   String str[]=new String[2];
        boolean result=false;
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select* from users where username=? and password=?",str);
        if(c.moveToFirst())
        {
            result=true;
        }
        return result;
    }
}
