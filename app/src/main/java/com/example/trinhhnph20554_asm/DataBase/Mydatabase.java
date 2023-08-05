package com.example.trinhhnph20554_asm.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    public Mydatabase(Context context) {
        super(context, "quanlicongviec", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_tb = "CREATE TABLE user_tb(username TEXT PRIMARY KEY,pass text,email TEXT,fullname TEXT )";
        db.execSQL(user_tb);
        String job_tb = "CREATE TABLE job_tb(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,content TEXT,startday TEXT,endday TEXT,id_status INTEGER REFERENCES status_tb (id) NOT NULL)";
        String status_tb = "CREATE TABLE status_tb (id INTEGER PRIMARY KEY AUTOINCREMENT, status TEXT (100) UNIQUE NOT NULL)";
        db.execSQL(status_tb);
        db.execSQL(job_tb);
       String data_user = "INSERT INTO user_tb  VALUES ('trinh','123','trinhhn@gmail.com','Hồ Nguyễn Trình')";
        db.execSQL(data_user);
        String data_status_tb = "INSERT INTO status_tb  VALUES (1,'ĐANG LÀM'),(2,'HOÀN THÀNH')";
        db.execSQL(data_status_tb);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE EXITST user_tb");
            db.execSQL("DROP TABLE EXITST status_tb");
            db.execSQL("DROP TABLE EXITST job_tb");

            onCreate(db);
        }
    }
}
